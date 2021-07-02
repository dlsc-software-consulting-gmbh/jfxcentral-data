package com.dlsc.jfxcentral.data;

import com.dlsc.jfxcentral.data.model.*;
import com.dlsc.jfxcentral.data.pull.PullRequest;
import com.dlsc.jfxcentral.data.util.QueryResult;
import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.*;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class DataRepository {

    public enum Source {

        LIVE("live"),
        STAGING("staging");

        private String branchName;

        Source(String branchName) {
            this.branchName = branchName;
        }

        public String getBranchName() {
            return branchName;
        }
    }

    public static boolean ASYNC = true;

    public static String BASE_URL = "https://raw.githubusercontent.com/dlemmermann/jfxcentral-data/";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private ExecutorService executor = Executors.newCachedThreadPool();

    private static DataRepository instance;

    private final Gson gson = Converters.registerLocalDate(new GsonBuilder()).setPrettyPrinting().create();

    private Map<Library, ObjectProperty<LibraryInfo>> libraryInfoMap = new HashMap<>();

    private Map<News, StringProperty> newsTextMap = new HashMap<>();

    private Map<Tutorial, StringProperty> tutorialTextMap = new HashMap<>();

    private Map<Download, StringProperty> downloadTextMap = new HashMap<>();

    private Map<Book, StringProperty> bookTextMap = new HashMap<>();

    private Map<Person, StringProperty> personDescriptionMap = new HashMap<>();

    private Map<Tool, StringProperty> toolDescriptionMap = new HashMap<>();

    private Map<RealWorldApp, StringProperty> realWorldAppDescriptionMap = new HashMap<>();

    private Map<Company, StringProperty> companyDescriptionMap = new HashMap<>();

    private Map<Library, StringProperty> libraryReadMeMap = new HashMap<>();

    private boolean loaded = false;

    public static synchronized DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }

        return instance;
    }

    public boolean isLoaded() {
        return loaded;
    }

    private DataRepository() {
        sourceProperty().addListener(it -> refreshData());

        if (ASYNC) {
            Thread thread = new Thread(() -> loadData());
            thread.setName("Data Repository Thread");
            thread.setDaemon(true);
            thread.start();
        } else {
            loadData();
        }
    }

    private void loadFeedsAndPullRequestsAsynchronously() {
        // update feeds and pull requests every 6 hours
        Thread updateFeedsAndPullRequestsThread = new Thread(() -> {
            try {
                loadFeeds();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FeedException e) {
                e.printStackTrace();
            }

            loadPullRequests();

            try {
                Thread.sleep(Duration.ofHours(6).toMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        updateFeedsAndPullRequestsThread.setName("Data Repository Feeds Update Thread");
        updateFeedsAndPullRequestsThread.setDaemon(true);
        updateFeedsAndPullRequestsThread.start();
    }

    public void refreshData() {
        clearData();
        loadData();

        try {
            loadFeeds();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        }

        loadPullRequests();
    }

    public void clearData() {
        loaded = false;

        setHomeText("");
        setOpenJFXText("");

        libraryInfoMap.clear();
        libraryReadMeMap.clear();
        newsTextMap.clear();
        personDescriptionMap.clear();
        companyDescriptionMap.clear();
        toolDescriptionMap.clear();
        realWorldAppDescriptionMap.clear();
        downloadTextMap.clear();
        bookTextMap.clear();
        tutorialTextMap.clear();

        getPosts().clear();
        getPeople().clear();
        getLibraries().clear();
        getBooks().clear();
        getNews().clear();
        getVideos().clear();
        getBlogs().clear();
        getCompanies().clear();
        getTools().clear();
        getRealWorldApps().clear();
        getDownloads().clear();
        getTutorials().clear();
    }

    private void loadData() {
        try {
            double steps = 14d;

            setProgress(0);
            setMessage("");

            setMessage("Loading introduction text");
            setHomeText(loadString(getBaseUrl() + "intro.md"));
            setProgress(getProgress() + 1 / steps);

            setMessage("Loading OpenJFX description");
            setOpenJFXText(loadString(getBaseUrl() + "openjfx/intro.md"));
            setProgress(getProgress() + 1 / steps);

            // load people
            setMessage("Loading index of people");
            File peopleFile = loadFile(getBaseUrl() + "people/people.json");
            setPeople(gson.fromJson(new FileReader(peopleFile), new TypeToken<List<Person>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            // load books
            setMessage("Loading index of books");
            File booksFile = loadFile(getBaseUrl() + "books/books.json");
            setBooks(gson.fromJson(new FileReader(booksFile), new TypeToken<List<Book>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            // load videos
            setMessage("Loading index of videos");
            File videosFile = loadFile(getBaseUrl() + "videos/videos.json");
            setVideos(gson.fromJson(new FileReader(videosFile), new TypeToken<List<Video>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            // load libraries
            setMessage("Loading index of libraries");
            File librariesFile = loadFile(getBaseUrl() + "libraries/libraries.json");
            setLibraries(gson.fromJson(new FileReader(librariesFile), new TypeToken<List<Library>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            // load libraries
            setMessage("Loading index of news");
            File newsFile = loadFile(getBaseUrl() + "news/news.json");
            setNews(gson.fromJson(new FileReader(newsFile), new TypeToken<List<News>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            // load libraries
            setMessage("Loading index of blogs");
            File blogsFile = loadFile(getBaseUrl() + "blogs/blogs.json");
            setBlogs(gson.fromJson(new FileReader(blogsFile), new TypeToken<List<Blog>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            // load libraries
            setMessage("Loading index of companies");
            File companiesFile = loadFile(getBaseUrl() + "companies/companies.json");
            setCompanies(gson.fromJson(new FileReader(companiesFile), new TypeToken<List<Company>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            // load tools
            setMessage("Loading index of tools");
            File toolsFile = loadFile(getBaseUrl() + "tools/tools.json");
            setTools(gson.fromJson(new FileReader(toolsFile), new TypeToken<List<Tool>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            // load real world apps
            setMessage("Loading index of real world apps");
            File realWorldFile = loadFile(getBaseUrl() + "realworld/realworld.json");
            setRealWorldApps(gson.fromJson(new FileReader(realWorldFile), new TypeToken<List<RealWorldApp>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            // load downloads
            setMessage("Loading index of downloads");
            File downloadsFile = loadFile(getBaseUrl() + "downloads/downloads.json");
            setDownloads(gson.fromJson(new FileReader(downloadsFile), new TypeToken<List<Download>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            // load downloads
            setMessage("Loading index of tutorials");
            File tutorialsFile = loadFile(getBaseUrl() + "tutorials/tutorials.json");
            setTutorials(gson.fromJson(new FileReader(tutorialsFile), new TypeToken<List<Tutorial>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            setMessage("Updating list of recent items");
            updateRecentItems();
            setProgress(getProgress() + 1 / steps);

            setMessage("Done loading");
            setProgress(1);

            if (ASYNC) {
                loadFeedsAndPullRequestsAsynchronously();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            loaded = true;
        }
    }

    private void updateRecentItems() {
        getRecentItems().clear();
        getRecentItems().addAll(findRecentItems(getNews()));
        getRecentItems().addAll(findRecentItems(getPeople()));
        getRecentItems().addAll(findRecentItems(getBooks()));
        getRecentItems().addAll(findRecentItems(getLibraries()));
        getRecentItems().addAll(findRecentItems(getVideos()));
        getRecentItems().addAll(findRecentItems(getBlogs()));
        getRecentItems().addAll(findRecentItems(getCompanies()));
        getRecentItems().addAll(findRecentItems(getPosts()));

        // newest ones on top
        Collections.sort(getRecentItems(), Comparator.comparing(ModelObject::getCreationOrUpdateDate).reversed());
    }

    private List<ModelObject> findRecentItems(List<? extends ModelObject> items) {
        List<ModelObject> result = new ArrayList<>();

        final LocalDate today = LocalDate.now();

        items.forEach(item -> {
            LocalDate date = item.getModifiedOn();
            if (date == null) {
                date = item.getCreatedOn();
            }
            if (date != null) {
                if (date.isAfter(today.minusWeeks(4))) {
                    result.add(item);
                }
            }
        });

        return result;
    }

    private final ListProperty<ModelObject> recentItems = new SimpleListProperty<>(this, "recentItems", FXCollections.observableArrayList());

    public ObservableList<ModelObject> getRecentItems() {
        return recentItems.get();
    }

    public ListProperty<ModelObject> recentItemsProperty() {
        return recentItems;
    }

    public void setRecentItems(ObservableList<ModelObject> recentItems) {
        this.recentItems.set(recentItems);
    }

    public Optional<Person> getPersonById(String id) {
        return people.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public Optional<Company> getCompanyById(String id) {
        return companies.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public Optional<Library> getLibraryById(String id) {
        return libraries.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public Optional<Book> getBookById(String id) {
        return books.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public Optional<Blog> getBlogById(String id) {
        return blogs.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public Optional<RealWorldApp> getRealWorldAppById(String id) {
        return realWorldApps.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public Optional<Tool> getToolById(String id) {
        return tools.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public Optional<Download> getDownloadById(String id) {
        return downloads.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public Optional<News> getNewsById(String id) {
        return news.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public Optional<Video> getVideoById(String id) {
        return videos.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public Optional<Tutorial> getTutorialById(String id) {
        return tutorials.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public <T extends ModelObject> ListProperty<T> getLinkedObjects(ModelObject modelObject, Class<T> clazz) {
        List<T> itemList = getList(clazz);
        List<String> idsList = getIdList(modelObject, clazz);
        ListProperty<T> listProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
        listProperty.setAll(itemList.stream().filter(item -> idsList.contains(item.getId()) || getIdList(item, modelObject.getClass()).contains(modelObject.getId())).collect(Collectors.toList()));
        return listProperty;
    }

    private <T extends ModelObject> List<String> getIdList(ModelObject modelObject, Class<T> clazz) {
        if (clazz.equals(Video.class)) {
            return modelObject.getVideoIds();
        } else if (clazz.equals(Book.class)) {
            return modelObject.getBookIds();
        } else if (clazz.equals(Library.class)) {
            return modelObject.getLibraryIds();
        } else if (clazz.equals(Tutorial.class)) {
            return modelObject.getTutorialIds();
        } else if (clazz.equals(Download.class)) {
            return modelObject.getDownloadIds();
        } else if (clazz.equals(Person.class)) {
            return modelObject.getPersonIds();
        } else if (clazz.equals(Tool.class)) {
            return modelObject.getToolIds();
        } else if (clazz.equals(RealWorldApp.class)) {
            return modelObject.getAppIds();
        } else if (clazz.equals(News.class)) {
            return modelObject.getNewsIds();
        } else if (clazz.equals(Blog.class)) {
            return modelObject.getBlogIds();
        } else if (clazz.equals(Company.class)) {
            return modelObject.getCompanyIds();
        }

        throw new IllegalArgumentException("unsupported class type: " + clazz.getSimpleName());
    }

    private <T extends ModelObject> List<T> getList(Class<T> clazz) {
        if (clazz.equals(Video.class)) {
            return (List<T>) videos.get();
        } else if (clazz.equals(Book.class)) {
            return (List<T>) books.get();
        } else if (clazz.equals(Library.class)) {
            return (List<T>) libraries.get();
        } else if (clazz.equals(Tutorial.class)) {
            return (List<T>) tutorials.get();
        } else if (clazz.equals(Download.class)) {
            return (List<T>) downloads.get();
        } else if (clazz.equals(Person.class)) {
            return (List<T>) people.get();
        } else if (clazz.equals(Tool.class)) {
            return (List<T>) tools.get();
        } else if (clazz.equals(RealWorldApp.class)) {
            return (List<T>) realWorldApps.get();
        } else if (clazz.equals(News.class)) {
            return (List<T>) news.get();
        } else if (clazz.equals(Blog.class)) {
            return (List<T>) blogs.get();
        } else if (clazz.equals(Company.class)) {
            return (List<T>) companies.get();
        }

        throw new IllegalArgumentException("unsupported class type: " + clazz.getSimpleName());
    }

    public ListProperty<Video> getVideosByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, Video.class);
    }

    public ListProperty<Download> getDownloadsByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, Download.class);
    }

    public ListProperty<Book> getBooksByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, Book.class);
    }

    public ListProperty<Tutorial> getTutorialsByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, Tutorial.class);
    }

    public ListProperty<Blog> getBlogsByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, Blog.class);
    }

    public ListProperty<Library> getLibrariesByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, Library.class);
    }

    public ListProperty<Tool> getToolsByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, Tool.class);
    }

    public ListProperty<News> getNewsByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, News.class);
    }

    public ListProperty<Company> getCompaniesByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, Company.class);
    }

    public ListProperty<RealWorldApp> getRealWorldAppsByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, RealWorldApp.class);
    }

    public ObjectProperty<LibraryInfo> libraryInfoProperty(Library library) {
        return libraryInfoMap.computeIfAbsent(library, key -> {
            ObjectProperty<LibraryInfo> infoProperty = new SimpleObjectProperty<>();

            if (ASYNC) {
                executor.submit(() -> loadLibraryInfoText(library, infoProperty));
            } else {
                loadLibraryInfoText(library, infoProperty);
            }

            return infoProperty;
        });
    }

    private void loadLibraryInfoText(Library library, ObjectProperty<LibraryInfo> infoProperty) {
        try {
            String libraryId = library.getId();
            File file = loadFile(getBaseUrl() + "libraries/" + libraryId + "/info.json");
            LibraryInfo result = gson.fromJson(new FileReader(file), LibraryInfo.class);
            if (ASYNC) {
                Platform.runLater(() -> infoProperty.set(result));
            } else {
                infoProperty.set(result);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public StringProperty newsTextProperty(News news) {
        return newsTextMap.computeIfAbsent(news, key -> {
            StringProperty textProperty = new SimpleStringProperty();

            if (ASYNC) {
                executor.submit(() -> loadNewsText(news, textProperty));
            } else {
                loadNewsText(news, textProperty);
            }

            return textProperty;
        });
    }

    private void loadNewsText(News news, StringProperty textProperty) {
        String url = getNewsBaseUrl(news) + "/text.md";
        System.out.println("loading news from: " + url);
        String text = loadString(url);
        if (ASYNC) {
            Platform.runLater(() -> textProperty.set(text));
        } else {
            textProperty.set(text);
        }
    }

    public StringProperty tutorialTextProperty(Tutorial tutorial) {
        return tutorialTextMap.computeIfAbsent(tutorial, key -> {
            StringProperty textProperty = new SimpleStringProperty();

            if (ASYNC) {
                executor.submit(() -> loadTutorialText(tutorial, textProperty));
            } else {
                loadTutorialText(tutorial, textProperty);
            }

            return textProperty;
        });
    }

    private void loadTutorialText(Tutorial tutorial, StringProperty textProperty) {
        String url = getBaseUrl() + "tutorials/" + tutorial.getId() + "/readme.md";
        System.out.println("loading tutorial from: " + url);
        String text = loadString(url);
        if (ASYNC) {
            Platform.runLater(() -> textProperty.set(text));
        } else {
            textProperty.set(text);
        }
    }

    public StringProperty downloadTextProperty(Download download) {
        return downloadTextMap.computeIfAbsent(download, key -> {
            StringProperty textProperty = new SimpleStringProperty();

            if (ASYNC) {
                executor.submit(() -> loadDownloadText(download, textProperty));
            } else {
                loadDownloadText(download, textProperty);
            }

            return textProperty;
        });
    }

    private void loadDownloadText(Download download, StringProperty textProperty) {
        String url = getBaseUrl() + "downloads/" + download.getId() + "/readme.md";
        System.out.println("loading download text from: " + url);
        String text = loadString(url);
        if (ASYNC) {
            Platform.runLater(() -> textProperty.set(text));
        } else {
            textProperty.set(text);
        }
    }

    public StringProperty bookTextProperty(Book book) {
        return bookTextMap.computeIfAbsent(book, key -> {
            StringProperty textProperty = new SimpleStringProperty();

            if (ASYNC) {
                executor.submit(() -> loadBookText(book, textProperty));
            } else {
                loadBookText(book, textProperty);
            }

            return textProperty;
        });
    }

    private void loadBookText(Book book, StringProperty textProperty) {
        String url = getBaseUrl() + "books/" + book.getId() + "/readme.md";
        System.out.println("loading book text from: " + url);
        String text = loadString(url);
        if (ASYNC) {
            Platform.runLater(() -> textProperty.set(text));
        } else {
            textProperty.set(text);
        }
    }

    public StringProperty personDescriptionProperty(Person person) {
        return personDescriptionMap.computeIfAbsent(person, key -> {
            StringProperty readmeProperty = new SimpleStringProperty();

            if (ASYNC) {
                executor.submit(() -> loadPersonDescription(person, readmeProperty));
            } else {
                loadPersonDescription(person, readmeProperty);
            }

            return readmeProperty;
        });
    }

    private void loadPersonDescription(Person person, StringProperty readmeProperty) {
        String readmeText = loadString(getBaseUrl() + "people/" + person.getId() + "/readme.md");
        if (ASYNC) {
            Platform.runLater(() -> readmeProperty.set(readmeText));
        } else {
            readmeProperty.set(readmeText);
        }
    }

    public StringProperty toolDescriptionProperty(Tool tool) {
        return toolDescriptionMap.computeIfAbsent(tool, key -> {
            StringProperty readmeProperty = new SimpleStringProperty();

            if (ASYNC) {
                executor.submit(() -> loadToolDescription(tool, readmeProperty));
            } else {
                loadToolDescription(tool, readmeProperty);
            }

            return readmeProperty;
        });
    }

    private void loadToolDescription(Tool tool, StringProperty readmeProperty) {
        String readmeText = loadString(getBaseUrl() + "tools/" + tool.getId() + "/readme.md");
        if (ASYNC) {
            Platform.runLater(() -> readmeProperty.set(readmeText));
        } else {
            readmeProperty.set(readmeText);
        }
    }

    public StringProperty realWorldAppDescriptionProperty(RealWorldApp app) {
        return realWorldAppDescriptionMap.computeIfAbsent(app, key -> {
            StringProperty readmeProperty = new SimpleStringProperty();

            if (ASYNC) {
                executor.submit(() -> loadRealWorldDescription(app, readmeProperty));
            } else {
                loadRealWorldDescription(app, readmeProperty);
            }

            return readmeProperty;
        });
    }

    private void loadRealWorldDescription(RealWorldApp app, StringProperty readmeProperty) {
        String readmeText = loadString(getBaseUrl() + "realworld/" + app.getId() + "/readme.md");
        if (ASYNC) {
            Platform.runLater(() -> readmeProperty.set(readmeText));
        } else {
            readmeProperty.set(readmeText);
        }
    }

    public StringProperty companyDescriptionProperty(Company company) {
        return companyDescriptionMap.computeIfAbsent(company, key -> {
            StringProperty readmeProperty = new SimpleStringProperty();

            if (ASYNC) {
                executor.submit(() -> loadCompanyDescription(company, readmeProperty));
            } else {
                loadCompanyDescription(company, readmeProperty);
            }

            return readmeProperty;
        });
    }

    private void loadCompanyDescription(Company company, StringProperty readmeProperty) {
        String readmeText = loadString(getBaseUrl() + "companies/" + company.getId() + "/readme.md");
        if (ASYNC) {
            Platform.runLater(() -> readmeProperty.set(readmeText));
        } else {
            readmeProperty.set(readmeText);
        }
    }

    public String getBaseUrl() {
        if (BASE_URL.startsWith("file:/")) { // for local tests
            return BASE_URL;
        }

        return BASE_URL + getSource().getBranchName() + "/";
    }

    public String getNewsBaseUrl(News news) {
        return getBaseUrl() + "news/" + DATE_FORMATTER.format(news.getCreatedOn()) + "-" + news.getId();
    }

    public StringProperty libraryReadMeProperty(Library library) {
        return libraryReadMeMap.computeIfAbsent(library, key -> {
            StringProperty readmeProperty = new SimpleStringProperty();

            if (ASYNC) {
                executor.submit(() -> loadLibraryDescription(library, readmeProperty));
            } else {
                loadLibraryDescription(library, readmeProperty);
            }

            return readmeProperty;
        });
    }

    private void loadLibraryDescription(Library library, StringProperty readmeProperty) {
        String readmeText = loadString(getBaseUrl() + "libraries/" + library.getId() + "/readme.md");
        if (ASYNC) {
            Platform.runLater(() -> readmeProperty.set(readmeText));
        } else {
            readmeProperty.set(readmeText);
        }
    }

    private final StringProperty homeText = new SimpleStringProperty(this, "homeText");

    public String getHomeText() {
        return homeText.get();
    }

    public StringProperty homeTextProperty() {
        return homeText;
    }

    public void setHomeText(String homeText) {
        this.homeText.set(homeText);
    }

    private final StringProperty openJFXText = new SimpleStringProperty(this, "openJFXText");

    public String getOpenJFXText() {
        return openJFXText.get();
    }

    public StringProperty openJFXTextProperty() {
        return openJFXText;
    }

    public void setOpenJFXText(String openJFXText) {
        this.openJFXText.set(openJFXText);
    }

    private final ListProperty<Library> libraries = new SimpleListProperty<>(this, "libraries", FXCollections.observableArrayList());

    public ObservableList<Library> getLibraries() {
        return libraries.get();
    }

    public ListProperty<Library> librariesProperty() {
        return libraries;
    }

    public void setLibraries(List<Library> libraries) {
        this.libraries.setAll(libraries);
        this.libraries.removeIf(item -> item.isHide());
    }

    private final ListProperty<Blog> blogs = new SimpleListProperty<>(this, "blogs", FXCollections.observableArrayList());

    public ObservableList<Blog> getBlogs() {
        return blogs.get();
    }

    public ListProperty<Blog> blogsProperty() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs.setAll(blogs);
        this.blogs.removeIf(item -> item.isHide());
    }

    private final ListProperty<PullRequest> pullRequests = new SimpleListProperty<>(this, "pullRequests", FXCollections.observableArrayList());

    public ObservableList<PullRequest> getPullRequests() {
        return pullRequests.get();
    }

    public ListProperty<PullRequest> pullRequestsProperty() {
        return pullRequests;
    }

    public void setPullRequests(List<PullRequest> pullRequests) {
        this.pullRequests.setAll(pullRequests);
    }

    private final ListProperty<News> news = new SimpleListProperty<>(this, "news", FXCollections.observableArrayList());

    public ObservableList<News> getNews() {
        return news.get();
    }

    public ListProperty<News> newsProperty() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news.setAll(news);
        this.news.removeIf(item -> item.isHide());
    }

    private final ListProperty<Book> books = new SimpleListProperty<>(this, "books", FXCollections.observableArrayList());

    public ObservableList<Book> getBooks() {
        return books.get();
    }

    public ListProperty<Book> booksProperty() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books.setAll(books);
        this.books.removeIf(item -> item.isHide());
    }

    private final ListProperty<Tutorial> tutorials = new SimpleListProperty<>(this, "tutorials", FXCollections.observableArrayList());

    public ObservableList<Tutorial> getTutorials() {
        return tutorials.get();
    }

    public ListProperty<Tutorial> tutorialsProperty() {
        return tutorials;
    }

    public void setTutorials(List<Tutorial> tutorials) {
        this.tutorials.setAll(tutorials);
        this.tutorials.removeIf(item -> item.isHide());
    }

    private final ListProperty<Video> videos = new SimpleListProperty<>(this, "videos", FXCollections.observableArrayList());

    public ObservableList<Video> getVideos() {
        return videos.get();
    }

    public ListProperty<Video> videosProperty() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos.setAll(videos);
        this.videos.removeIf(item -> item.isHide());
    }

    private final ListProperty<Download> downloads = new SimpleListProperty<>(this, "downloads", FXCollections.observableArrayList());


    public ObservableList<Download> getDownloads() {
        return downloads.get();
    }

    public ListProperty<Download> downloadsProperty() {
        return downloads;
    }

    public void setDownloads(List<Download> downloads) {
        this.downloads.setAll(downloads);
        this.downloads.removeIf(item -> item.isHide());
    }

    private final ListProperty<RealWorldApp> realWorldApps = new SimpleListProperty<>(this, "realWorldApps", FXCollections.observableArrayList());

    public ObservableList<RealWorldApp> getRealWorldApps() {
        return realWorldApps.get();
    }

    public ListProperty<RealWorldApp> realWorldAppsProperty() {
        return realWorldApps;
    }

    public void setRealWorldApps(List<RealWorldApp> realWorldApps) {
        this.realWorldApps.setAll(realWorldApps);
        this.realWorldApps.removeIf(item -> item.isHide());
    }

    private final ListProperty<Tool> tools = new SimpleListProperty<>(this, "tools", FXCollections.observableArrayList());

    public ObservableList<Tool> getTools() {
        return tools.get();
    }

    public ListProperty<Tool> toolsProperty() {
        return tools;
    }

    public void setTools(List<Tool> tools) {
        this.tools.setAll(tools);
        this.tools.removeIf(item -> item.isHide());
    }

    private final ListProperty<Company> companies = new SimpleListProperty<>(this, "companies", FXCollections.observableArrayList());

    public ObservableList<Company> getCompanies() {
        return companies.get();
    }

    public ListProperty<Company> companiesProperty() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies.setAll(companies);
        this.companies.removeIf(item -> item.isHide());
    }

    private final ListProperty<Person> people = new SimpleListProperty<>(this, "people", FXCollections.observableArrayList());

    public ObservableList<Person> getPeople() {
        return people.get();
    }

    public ListProperty<Person> peopleProperty() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people.setAll(people);
        this.people.removeIf(item -> item.isHide());
    }

    private File loadFile(String urlString) throws IOException {
        try {
            return new File(new URI(urlString));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String loadString(String address) {
        System.out.println("loading string from: " + address);

        StringBuilder sb = new StringBuilder();
        try {
            // read text returned by server
            BufferedReader in = new BufferedReader(new FileReader(new File(new URI(address))));

            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            in.close();

        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    private final ObjectProperty<Source> source = new SimpleObjectProperty<>(this, "source", Source.LIVE);

    public Source getSource() {
        return source.get();
    }

    public ObjectProperty<Source> sourceProperty() {
        return source;
    }

    public void setSource(Source source) {
        this.source.set(source);
    }

    public StringProperty getArtifactVersion(Coordinates coordinates) {

        String groupId = coordinates.getGroupId();
        String artifactId = coordinates.getArtifactId();

        StringProperty result = new SimpleStringProperty("");

        if (StringUtils.isNotBlank(groupId) && StringUtils.isNotBlank(artifactId)) {
            if (ASYNC) {
                executor.execute(() -> loadArtifactVersion(groupId, artifactId, result));
            } else {
                loadArtifactVersion(groupId, artifactId, result);
            }
        }

        return result;
    }

    private void loadArtifactVersion(String groupId, String artifactId, StringProperty result) {
        HttpURLConnection con = null;

        try {
            URL url = new URL(MessageFormat.format("http://search.maven.org/solrsearch/select?q=g:{0}+AND+a:{1}", groupId, artifactId));

            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setUseCaches(false);

            int status = con.getResponseCode();
            if (status == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                QueryResult queryResult = gson.fromJson(content.toString(), QueryResult.class);

                if (ASYNC) {
                    Platform.runLater(() -> result.set(queryResult.getResponse().getDocs().get(0).getLatestVersion()));
                } else {
                    result.set(queryResult.getResponse().getDocs().get(0).getLatestVersion());
                }
            } else {
                result.set("unknown");
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    private final ListProperty<Post> posts = new SimpleListProperty<>(this, "posts", FXCollections.observableArrayList());

    public ObservableList<Post> getPosts() {
        return posts.get();
    }

    public ListProperty<Post> postsProperty() {
        return posts;
    }

    private final BooleanProperty loadingFeeds = new SimpleBooleanProperty(this, "loadingFeeds", false);

    public boolean isLoadingFeeds() {
        return loadingFeeds.get();
    }

    public BooleanProperty loadingFeedsProperty() {
        return loadingFeeds;
    }

    public void setLoadingFeeds(boolean loadingFeeds) {
        this.loadingFeeds.set(loadingFeeds);
    }

    private final BooleanProperty loadingPullRequests = new SimpleBooleanProperty(this, "loadingPullRequests", false);

    public boolean isLoadingPullRequests() {
        return loadingPullRequests.get();
    }

    public BooleanProperty loadingPullRequestsProperty() {
        return loadingPullRequests;
    }

    public void setLoadingPullRequests(boolean loadingPullRequests) {
        this.loadingPullRequests.set(loadingPullRequests);
    }

    public void loadFeeds() throws IOException, FeedException {
        System.out.println("loading feeds");

        setLoadingFeeds(true);

        try {
            Platform.runLater(() -> getPosts().clear());

            ObservableList<Blog> blogObservableList = getBlogs();
            int size = blogObservableList.size();

            System.out.println("loading feeds from " + size + " blogs");

            for (int i = 0; i < size; i++) {
                Blog blog = blogObservableList.get(i);
                String url = blog.getFeed();
                if (StringUtils.isNotBlank(url)) {

                    System.out.println("loading blog posts from url: " + url);
                    setMessage("Loading blog: " + blog.getName());

                    SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));

                    List<SyndEntry> entries = feed.getEntries();

                    if (ASYNC) {
                        Platform.runLater(() -> entries.forEach(entry -> getPosts().add(new Post(blog, feed, entry))));
                    } else {
                        entries.forEach(entry -> getPosts().add(new Post(blog, feed, entry)));
                    }

                    if (!ASYNC && !entries.isEmpty()) {
                        // to save time when unit tests are running
                        break;
                    }
                }
            }
        } finally {
            setLoadingFeeds(false);
        }
    }

    public void loadPullRequests() {
        System.out.println("loading pull requests");

        setLoadingPullRequests(true);

        try {
            Platform.runLater(() -> getPullRequests().clear());

            HttpURLConnection con = null;

            for (int page = 1; page < 2; page++) {
                try {
                    URL url = new URL("https://api.github.com/repos/openjdk/jfx/pulls?state=all&per_page=100&page=" + page);

                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setUseCaches(false);

                    int status = con.getResponseCode();
                    if (status == 200) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String inputLine;
                        StringBuffer content = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                            content.append(inputLine);
                        }
                        in.close();

                        List<PullRequest> result = gson.fromJson(content.toString(), new TypeToken<List<PullRequest>>() {
                        }.getType());

                        if (ASYNC) {
                            Platform.runLater(() -> getPullRequests().addAll(result));
                        } else {
                            getPullRequests().addAll(result);
                        }
                    }
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (con != null) {
                        con.disconnect();
                    }
                }
            }
        } finally {
            setLoadingPullRequests(false);
        }
    }

    public List<ModelObject> search(String pattern) {
        List<ModelObject> result = new ArrayList<>();
        search(getBooks(), pattern, result);
        search(getBlogs(), pattern, result);
        search(getCompanies(), pattern, result);
        search(getPeople(), pattern, result);
        search(getLibraries(), pattern, result);
        search(getRealWorldApps(), pattern, result);
        search(getTools(), pattern, result);
        search(getVideos(), pattern, result);
        search(getNews(), pattern, result);
        search(getDownloads(), pattern, result);
        return result;
    }

    private void search(List<? extends ModelObject> modelObjects, String pattern, List<ModelObject> result) {
        modelObjects.forEach(mo -> {
            if (mo.matches(pattern)) {
                result.add(mo);
            }
        });
    }

    private final StringProperty message = new SimpleStringProperty(this, "message");

    public String getMessage() {
        return message.get();
    }

    public StringProperty messageProperty() {
        return message;
    }

    public void setMessage(String message) {
        this.message.set(message);
    }

    private final DoubleProperty progress = new SimpleDoubleProperty(this, "progress");

    public double getProgress() {
        return progress.get();
    }

    public DoubleProperty progressProperty() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress.set(progress);
    }
}
