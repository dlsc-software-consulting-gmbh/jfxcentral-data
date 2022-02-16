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
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.*;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DataRepository extends Application {

    private static final Logger LOG = Logger.getLogger(DataRepository.class.getName());

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

    public static File REPO_DIRECTORY = new File(System.getProperty("user.home"), ".jfxcentralrepo");

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

    private Map<Tip, StringProperty> tipDescriptionMap = new HashMap<>();

    private Map<RealWorldApp, StringProperty> realWorldAppDescriptionMap = new HashMap<>();

    private Map<Company, StringProperty> companyDescriptionMap = new HashMap<>();

    private Map<Library, StringProperty> libraryReadMeMap = new HashMap<>();

    private Map<LinksOfTheWeek, StringProperty> linksOfTheWeekReadMeMap = new HashMap<>();

    private boolean loaded;

    public static synchronized DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
            instance.loadData();
        }

        return instance;
    }

    public boolean isLoaded() {
        return loaded;
    }

    private DataRepository() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    public void loadData() {
        if (ASYNC) {
            Platform.runLater(() -> {
                Thread thread = new Thread(() -> doLoadData("call to loadData() method"));
                thread.setName("Data Repository Refresh Thread");
                thread.setDaemon(true);
                thread.start();
            });
        } else {
            doLoadData("explicit call to refresh method");
        }
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
        tipDescriptionMap.clear();
        realWorldAppDescriptionMap.clear();
        downloadTextMap.clear();
        bookTextMap.clear();
        tutorialTextMap.clear();
        linksOfTheWeekReadMeMap.clear();

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
        getTips().clear();
        getLinksOfTheWeek().clear();
    }

    private void doLoadData(String reason) {
        LOG.fine("loading data, reason = " + reason);

        try {
            String homeText = loadString(new File(getRepositoryDirectory(), "intro.md"));

            String openJFXText = loadString(new File(getRepositoryDirectory(), "openjfx/intro.md"));

            // load people
            File peopleFile = new File(getRepositoryDirectory(), "people/people.json");
            List<Person> people = gson.fromJson(new FileReader(peopleFile), new TypeToken<List<Person>>() {
            }.getType());

            // load books
            File booksFile = new File(getRepositoryDirectory(), "books/books.json");
            List<Book> books = gson.fromJson(new FileReader(booksFile), new TypeToken<List<Book>>() {
            }.getType());

            // load videos
            File videosFile = new File(getRepositoryDirectory(), "videos/videos.json");
            List<Video> videos = gson.fromJson(new FileReader(videosFile), new TypeToken<List<Video>>() {
            }.getType());

            // load libraries
            File librariesFile = new File(getRepositoryDirectory(), "libraries/libraries.json");
            List<Library> libraries = gson.fromJson(new FileReader(librariesFile), new TypeToken<List<Library>>() {
            }.getType());

            // load libraries
            File newsFile = new File(getRepositoryDirectory(), "news/news.json");
            List<News> news = gson.fromJson(new FileReader(newsFile), new TypeToken<List<News>>() {
            }.getType());

            // load libraries
            File blogsFile = new File(getRepositoryDirectory(), "blogs/blogs.json");
            List<Blog> blogs = gson.fromJson(new FileReader(blogsFile), new TypeToken<List<Blog>>() {
            }.getType());

            // load libraries
            File companiesFile = new File(getRepositoryDirectory(), "companies/companies.json");
            List<Company> companies = gson.fromJson(new FileReader(companiesFile), new TypeToken<List<Company>>() {
            }.getType());

            // load tools
            File toolsFile = new File(getRepositoryDirectory(), "tools/tools.json");
            List<Tool> tools = gson.fromJson(new FileReader(toolsFile), new TypeToken<List<Tool>>() {
            }.getType());

            // load real world apps
            File realWorldFile = new File(getRepositoryDirectory(), "realworld/realworld.json");
            List<RealWorldApp> realWorldApps = gson.fromJson(new FileReader(realWorldFile), new TypeToken<List<RealWorldApp>>() {
            }.getType());

            // load downloads
            File downloadsFile = new File(getRepositoryDirectory(), "downloads/downloads.json");
            List<Download> downloads = gson.fromJson(new FileReader(downloadsFile), new TypeToken<List<Download>>() {
            }.getType());

            // load downloads
            File tutorialsFile = new File(getRepositoryDirectory(), "tutorials/tutorials.json");
            List<Tutorial> tutorials = gson.fromJson(new FileReader(tutorialsFile), new TypeToken<List<Tutorial>>() {
            }.getType());

            // load downloads
            File tipsFile = new File(getRepositoryDirectory(), "tips/tips.json");
            List<Tip> tips = gson.fromJson(new FileReader(tipsFile), new TypeToken<List<Tip>>() {
            }.getType());

            // load downloads
            File linksOfTheWeekFile = new File(getRepositoryDirectory(), "links/links.json");
            List<LinksOfTheWeek> links = gson.fromJson(new FileReader(linksOfTheWeekFile), new TypeToken<List<LinksOfTheWeek>>() {
            }.getType());

            if (ASYNC) {
                Platform.runLater(() -> setData(homeText, openJFXText, people, books, videos, libraries, news, blogs, companies, tools, realWorldApps, downloads, tutorials, tips, links));
            } else {
                setData(homeText, openJFXText, people, books, videos, libraries, news, blogs, companies, tools, realWorldApps, downloads, tutorials, tips, links);
            }

            LOG.fine("data loading finished");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            loaded = true;
        }
    }

    private void setData(String homeText, String openJFXText, List<Person> people, List<Book> books, List<Video> videos, List<Library> libraries,
                         List<News> news, List<Blog> blogs, List<Company> companies, List<Tool> tools, List<RealWorldApp> realWorldApps, List<Download> downloads,
                         List<Tutorial> tutorials, List<Tip> tips, List<LinksOfTheWeek> links) {
        clearData();

        setOpenJFXText(openJFXText);
        setHomeText(homeText);

        setPeople(people);
        setBooks(books);
        setVideos(videos);
        setLibraries(libraries);
        setNews(news);
        setBlogs(blogs);
        setCompanies(companies);
        setTools(tools);
        setRealWorldApps(realWorldApps);
        setDownloads(downloads);
        setTutorials(tutorials);
        setTips(tips);
        setLinksOfTheWeek(links);

        List<ModelObject> recentItems = findRecentItems();
        setRecentItems(recentItems);
    }

    private List<ModelObject> findRecentItems() {
        List<ModelObject> result = new ArrayList<>();
        result.addAll(findRecentItems(getNews()));
        result.addAll(findRecentItems(getPeople()));
        result.addAll(findRecentItems(getBooks()));
        result.addAll(findRecentItems(getLibraries()));
        result.addAll(findRecentItems(getVideos()));
        result.addAll(findRecentItems(getBlogs()));
        result.addAll(findRecentItems(getCompanies()));
        result.addAll(findRecentItems(getTools()));
        result.addAll(findRecentItems(getTutorials()));
        result.addAll(findRecentItems(getRealWorldApps()));
        result.addAll(findRecentItems(getDownloads()));
        result.addAll(findRecentItems(getTips()));
        result.addAll(findRecentItems(getLinksOfTheWeek()));

        // newest ones on top
        Collections.sort(result, Comparator.comparing(ModelObject::getCreationOrUpdateDate).reversed());

        return result;
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
                if (date.isAfter(today.minusWeeks(8))) {
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

    public void setRecentItems(List<ModelObject> recentItems) {
        this.recentItems.setAll(recentItems);
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

    public Optional<Tip> getTipById(String id) {
        return tips.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public Optional<LinksOfTheWeek> getLinksOfTheWeekById(String id) {
        return linksOfTheWeek.stream().filter(item -> item.getId().equals(id)).findFirst();
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
        } else if (clazz.equals(Tip.class)) {
            return modelObject.getTipIds();
        } else if (clazz.equals(LinksOfTheWeek.class)) {
            return modelObject.getLinksOfTheWeekIds();
        }

        throw new IllegalArgumentException("unsupported class type: " + clazz.getSimpleName());
    }

    public <T extends ModelObject> List<T> getList(Class<T> clazz) {
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
        } else if (clazz.equals(Tip.class)) {
            return (List<T>) tips.get();
        } else if (clazz.equals(LinksOfTheWeek.class)) {
            return (List<T>) linksOfTheWeek.get();
        }

        throw new IllegalArgumentException("unsupported class type: " + clazz.getSimpleName());
    }

    public ModelObject getByID(Class<? extends ModelObject> clz, String id) {
        return getList(clz).stream().filter(item -> item.getId().equals(id)).findFirst().get();
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

    public ListProperty<Person> getPeopleByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, Person.class);
    }

    public ListProperty<Tip> getTipsByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, Tip.class);
    }

    public ListProperty<LinksOfTheWeek> getLinksOfTheWeekByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, LinksOfTheWeek.class);
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
            File file = new File(getRepositoryDirectory(), "libraries/" + libraryId + "/info.json");
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
        String text = loadString(new File(getNewsDirectory(news), "/text.md"));
        if (ASYNC) {
            Platform.runLater(() -> textProperty.set(text));
        } else {
            textProperty.set(text);
        }
    }

    public StringProperty linksOfTheWeekTextProperty(LinksOfTheWeek links) {
        return linksOfTheWeekReadMeMap.computeIfAbsent(links, key -> {
            StringProperty textProperty = new SimpleStringProperty();

            if (ASYNC) {
                executor.submit(() -> loadLinksOfTheWeekText(links, textProperty));
            } else {
                loadLinksOfTheWeekText(links, textProperty);
            }

            return textProperty;
        });
    }

    private void loadLinksOfTheWeekText(LinksOfTheWeek links, StringProperty textProperty) {
        String text = loadString(new File(getRepositoryDirectory(), "links/" + links.getId() + "/readme.md"));
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
        String text = loadString(new File(getRepositoryDirectory(), "tutorials/" + tutorial.getId() + "/readme.md"));
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
        String text = loadString(new File(getRepositoryDirectory(), "downloads/" + download.getId() + "/readme.md"));
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
        String text = loadString(new File(getRepositoryDirectory(), "books/" + book.getId() + "/readme.md"));
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
        String readmeText = loadString(new File(getRepositoryDirectory(), "people/" + person.getId() + "/readme.md"));
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
        String readmeText = loadString(new File(getRepositoryDirectory(), "tools/" + tool.getId() + "/readme.md"));
        if (ASYNC) {
            Platform.runLater(() -> readmeProperty.set(readmeText));
        } else {
            readmeProperty.set(readmeText);
        }
    }

    public StringProperty tipDescriptionProperty(Tip tip) {
        return tipDescriptionMap.computeIfAbsent(tip, key -> {
            StringProperty readmeProperty = new SimpleStringProperty();

            if (ASYNC) {
                executor.submit(() -> loadTipDescription(tip, readmeProperty));
            } else {
                loadTipDescription(tip, readmeProperty);
            }

            return readmeProperty;
        });
    }

    private void loadTipDescription(Tip tip, StringProperty readmeProperty) {
        String readmeText = loadString(new File(getRepositoryDirectory(), "tips/" + tip.getId() + "/readme.md"));
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
        String readmeText = loadString(new File(getRepositoryDirectory(), "realworld/" + app.getId() + "/readme.md"));
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
        String readmeText = loadString(new File(getRepositoryDirectory(), "companies/" + company.getId() + "/readme.md"));
        if (ASYNC) {
            Platform.runLater(() -> readmeProperty.set(readmeText));
        } else {
            readmeProperty.set(readmeText);
        }
    }

    public File getRepositoryDirectory() {
        return REPO_DIRECTORY;
    }

    public File getNewsDirectory(News news) {
        return new File(getRepositoryDirectory(), "news/" + DATE_FORMATTER.format(news.getCreatedOn()) + "-" + news.getId());
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
        String readmeText = loadString(new File(getRepositoryDirectory(), "libraries/" + library.getId() + "/readme.md"));
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
        libraries.removeIf(l -> l.isHide());
        this.libraries.setAll(libraries);
    }

    private final ListProperty<Blog> blogs = new SimpleListProperty<>(this, "blogs", FXCollections.observableArrayList());

    public ObservableList<Blog> getBlogs() {
        return blogs.get();
    }

    public ListProperty<Blog> blogsProperty() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        blogs.removeIf(b -> b.isHide());
        this.blogs.setAll(blogs);
    }

    private final ListProperty<News> news = new SimpleListProperty<>(this, "news", FXCollections.observableArrayList());

    public ObservableList<News> getNews() {
        return news.get();
    }

    public ListProperty<News> newsProperty() {
        return news;
    }

    public void setNews(List<News> news) {
        news.removeIf(item -> item.isHide());
        this.news.setAll(news);
    }

    private final ListProperty<Book> books = new SimpleListProperty<>(this, "books", FXCollections.observableArrayList());

    public ObservableList<Book> getBooks() {
        return books.get();
    }

    public ListProperty<Book> booksProperty() {
        return books;
    }

    public void setBooks(List<Book> books) {
        books.removeIf(item -> item.isHide());
        this.books.setAll(books);
    }

    private final ListProperty<LinksOfTheWeek> linksOfTheWeek = new SimpleListProperty<>(this, "linksOfTheWeeks", FXCollections.observableArrayList());

    public ObservableList<LinksOfTheWeek> getLinksOfTheWeek() {
        return linksOfTheWeek.get();
    }

    public ListProperty<LinksOfTheWeek> linksOfTheWeekProperty() {
        return linksOfTheWeek;
    }

    public void setLinksOfTheWeek(List<LinksOfTheWeek> linksOfTheWeek) {
        this.linksOfTheWeek.setAll(linksOfTheWeek);
    }

    private final ListProperty<Tip> tips = new SimpleListProperty<>(this, "tips", FXCollections.observableArrayList());

    public ObservableList<Tip> getTips() {
        return tips.get();
    }

    public ListProperty<Tip> tipsProperty() {
        return tips;
    }

    public void setTips(List<Tip> tips) {
        this.tips.setAll(tips);
    }

    private final ListProperty<Tutorial> tutorials = new SimpleListProperty<>(this, "tutorials", FXCollections.observableArrayList());

    public ObservableList<Tutorial> getTutorials() {
        return tutorials.get();
    }

    public ListProperty<Tutorial> tutorialsProperty() {
        return tutorials;
    }

    public void setTutorials(List<Tutorial> tutorials) {
        tutorials.removeIf(item -> item.isHide());
        this.tutorials.setAll(tutorials);
    }

    private final ListProperty<Video> videos = new SimpleListProperty<>(this, "videos", FXCollections.observableArrayList());

    public ObservableList<Video> getVideos() {
        return videos.get();
    }

    public ListProperty<Video> videosProperty() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        videos.removeIf(item -> item.isHide());
        this.videos.setAll(videos);
    }

    private final ListProperty<Download> downloads = new SimpleListProperty<>(this, "downloads", FXCollections.observableArrayList());


    public ObservableList<Download> getDownloads() {
        return downloads.get();
    }

    public ListProperty<Download> downloadsProperty() {
        return downloads;
    }

    public void setDownloads(List<Download> downloads) {
        downloads.removeIf(item -> item.isHide());
        this.downloads.setAll(downloads);
    }

    private final ListProperty<RealWorldApp> realWorldApps = new SimpleListProperty<>(this, "realWorldApps", FXCollections.observableArrayList());

    public ObservableList<RealWorldApp> getRealWorldApps() {
        return realWorldApps.get();
    }

    public ListProperty<RealWorldApp> realWorldAppsProperty() {
        return realWorldApps;
    }

    public void setRealWorldApps(List<RealWorldApp> realWorldApps) {
        realWorldApps.removeIf(item -> item.isHide());
        this.realWorldApps.setAll(realWorldApps);
    }

    private final ListProperty<Tool> tools = new SimpleListProperty<>(this, "tools", FXCollections.observableArrayList());

    public ObservableList<Tool> getTools() {
        return tools.get();
    }

    public ListProperty<Tool> toolsProperty() {
        return tools;
    }

    public void setTools(List<Tool> tools) {
        tools.removeIf(item -> item.isHide());
        this.tools.setAll(tools);
    }

    private final ListProperty<Company> companies = new SimpleListProperty<>(this, "companies", FXCollections.observableArrayList());

    public ObservableList<Company> getCompanies() {
        return companies.get();
    }

    public ListProperty<Company> companiesProperty() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        companies.removeIf(item -> item.isHide());
        this.companies.setAll(companies);
    }

    private final ListProperty<Person> people = new SimpleListProperty<>(this, "people", FXCollections.observableArrayList());

    public ObservableList<Person> getPeople() {
        return people.get();
    }

    public ListProperty<Person> peopleProperty() {
        return people;
    }

    public void setPeople(List<Person> people) {
        people.removeIf(item -> item.isHide());
        this.people.setAll(people);
    }

    private String loadString(File file) {
        LOG.fine("loading string from: " + file);

        StringBuilder sb = new StringBuilder();
        try {
            // read text returned by server
            BufferedReader in = new BufferedReader(new FileReader(file));

            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            in.close();

        } catch (MalformedURLException e) {
            LOG.fine("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            LOG.fine("I/O Error: " + e.getMessage());
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
            URL url = new URL(MessageFormat.format("https://search.maven.org/solrsearch/select?q=g:{0}+AND+a:{1}", groupId, artifactId));

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
                    if (!queryResult.getResponse().getDocs().isEmpty()) {
                        Platform.runLater(() -> result.set(queryResult.getResponse().getDocs().get(0).getLatestVersion()));
                    }
                } else {
                    result.set(queryResult.getResponse().getDocs().get(0).getLatestVersion());
                }
            } else {
                if (ASYNC) {
                    Platform.runLater(() -> result.set("unknown"));
                } else {
                    result.set("unknown");
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

    public List<Post> loadPosts(Blog blog) {
        LOG.fine("loading posts for blog " + blog.getName());

        try {
            String url = blog.getFeed();
            if (StringUtils.isNotBlank(url)) {
                SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
                List<SyndEntry> entries = feed.getEntries();
                List<Post> posts = new ArrayList<>();
                entries.forEach(entry -> posts.add(new Post(blog, feed, entry)));
                return posts;
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private long cachedPullrequestsTime;

    private long timeToReloadSeconds = 600;

    private List<PullRequest> cachedPullRequests;

    public List<PullRequest> loadPullRequests() {
        long time = System.currentTimeMillis() / 1000;
        if (cachedPullrequestsTime + timeToReloadSeconds > time) {
            return cachedPullRequests;
        }
        cachedPullrequestsTime = time;
        cachedPullRequests = loadPullRequestsImpl();
        return cachedPullRequests;

    }

    private List<PullRequest> loadPullRequestsImpl() {
        LOG.fine("loading pull requests");

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

                    return gson.fromJson(content.toString(), new TypeToken<List<PullRequest>>() {
                    }.getType());
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

        return Collections.emptyList();
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
        search(getTutorials(), pattern, result);
        search(getTips(), pattern, result);
        return result;
    }

    private void search(List<? extends ModelObject> modelObjects, String pattern, List<ModelObject> result) {
        modelObjects.forEach(mo -> {
            if (mo.matches(pattern)) {
                result.add(mo);
            }
        });
    }
}
