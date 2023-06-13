package com.dlsc.jfxcentral.data;

import com.dlsc.jfxcentral.data.model.Blog;
import com.dlsc.jfxcentral.data.model.Book;
import com.dlsc.jfxcentral.data.model.Company;
import com.dlsc.jfxcentral.data.model.Coordinates;
import com.dlsc.jfxcentral.data.model.Download;
import com.dlsc.jfxcentral.data.model.IkonliPack;
import com.dlsc.jfxcentral.data.model.Library;
import com.dlsc.jfxcentral.data.model.LibraryInfo;
import com.dlsc.jfxcentral.data.model.LinksOfTheWeek;
import com.dlsc.jfxcentral.data.model.ModelObject;
import com.dlsc.jfxcentral.data.model.News;
import com.dlsc.jfxcentral.data.model.Person;
import com.dlsc.jfxcentral.data.model.Post;
import com.dlsc.jfxcentral.data.model.RealWorldApp;
import com.dlsc.jfxcentral.data.model.Tip;
import com.dlsc.jfxcentral.data.model.Tool;
import com.dlsc.jfxcentral.data.model.Tutorial;
import com.dlsc.jfxcentral.data.model.Video;
import com.dlsc.jfxcentral.data.pull.PullRequest;
import com.dlsc.jfxcentral.data.util.QueryResult;
import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DataRepository {

    private static final Logger LOG = Logger.getLogger(DataRepository.class.getName());

    public static File REPO_DIRECTORY = new File(System.getProperty("jfxcentral.repo", new File(System.getProperty("user.home"), ".jfxcentralrepo").getAbsolutePath())).getAbsoluteFile();

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

    public static boolean testing = false;

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
    }

    public void loadData() {
        doLoadData("explicit call to refresh method");
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
        getIkonliPacks().clear();
    }

    private void doLoadData(String reason) {
        LOG.fine("loading data, reason = " + reason);

        try {
            String homeText = loadString(new File(getRepositoryDirectory(), "intro.md"));

            String openJFXText = loadString(new File(getRepositoryDirectory(), "openjfx/intro.md"));

            // load people
            File peopleFile = new File(getRepositoryDirectory(), "people/people.json");
            List<Person> people = gson.fromJson(new FileReader(peopleFile, StandardCharsets.UTF_8), new TypeToken<List<Person>>() {
            }.getType());

            // load books
            File booksFile = new File(getRepositoryDirectory(), "books/books.json");
            List<Book> books = gson.fromJson(new FileReader(booksFile, StandardCharsets.UTF_8), new TypeToken<List<Book>>() {
            }.getType());

            // load videos
            File videosFile = new File(getRepositoryDirectory(), "videos/videos.json");
            List<Video> videos = gson.fromJson(new FileReader(videosFile, StandardCharsets.UTF_8), new TypeToken<List<Video>>() {
            }.getType());

            // load libraries
            File librariesFile = new File(getRepositoryDirectory(), "libraries/libraries.json");
            List<Library> libraries = gson.fromJson(new FileReader(librariesFile, StandardCharsets.UTF_8), new TypeToken<List<Library>>() {
            }.getType());

            // load libraries
            File newsFile = new File(getRepositoryDirectory(), "news/news.json");
            List<News> news = gson.fromJson(new FileReader(newsFile, StandardCharsets.UTF_8), new TypeToken<List<News>>() {
            }.getType());

            // load libraries
            File blogsFile = new File(getRepositoryDirectory(), "blogs/blogs.json");
            List<Blog> blogs = gson.fromJson(new FileReader(blogsFile, StandardCharsets.UTF_8), new TypeToken<List<Blog>>() {
            }.getType());

            // load libraries
            File companiesFile = new File(getRepositoryDirectory(), "companies/companies.json");
            List<Company> companies = gson.fromJson(new FileReader(companiesFile, StandardCharsets.UTF_8), new TypeToken<List<Company>>() {
            }.getType());

            // load tools
            File toolsFile = new File(getRepositoryDirectory(), "tools/tools.json");
            List<Tool> tools = gson.fromJson(new FileReader(toolsFile, StandardCharsets.UTF_8), new TypeToken<List<Tool>>() {
            }.getType());

            // load real world apps
            File realWorldFile = new File(getRepositoryDirectory(), "realworld/realworld.json");
            List<RealWorldApp> realWorldApps = gson.fromJson(new FileReader(realWorldFile, StandardCharsets.UTF_8), new TypeToken<List<RealWorldApp>>() {
            }.getType());

            // load downloads
            File downloadsFile = new File(getRepositoryDirectory(), "downloads/downloads.json");
            List<Download> downloads = gson.fromJson(new FileReader(downloadsFile, StandardCharsets.UTF_8), new TypeToken<List<Download>>() {
            }.getType());

            // load downloads
            File tutorialsFile = new File(getRepositoryDirectory(), "tutorials/tutorials.json");
            List<Tutorial> tutorials = gson.fromJson(new FileReader(tutorialsFile, StandardCharsets.UTF_8), new TypeToken<List<Tutorial>>() {
            }.getType());

            // load downloads
            File tipsFile = new File(getRepositoryDirectory(), "tips/tips.json");
            List<Tip> tips = gson.fromJson(new FileReader(tipsFile, StandardCharsets.UTF_8), new TypeToken<List<Tip>>() {
            }.getType());

            // load downloads
            File linksOfTheWeekFile = new File(getRepositoryDirectory(), "links/links.json");
            List<LinksOfTheWeek> links = gson.fromJson(new FileReader(linksOfTheWeekFile, StandardCharsets.UTF_8), new TypeToken<List<LinksOfTheWeek>>() {
            }.getType());

            // load ikonlipacks
            File ikonliPacksFile = new File(getRepositoryDirectory(), "ikonlipacks/ikonlipacks.json");
            List<IkonliPack> ikonliPacks = gson.fromJson(new FileReader(ikonliPacksFile, StandardCharsets.UTF_8), new TypeToken<List<IkonliPack>>() {
            }.getType());

            setData(homeText, openJFXText, people, books, videos, libraries, news, blogs, companies, tools, realWorldApps, downloads, tutorials, tips, links, ikonliPacks);

            LOG.fine("data loading finished");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            loaded = true;
        }
    }

    private void setData(String homeText, String openJFXText, List<Person> people, List<Book> books, List<Video> videos, List<Library> libraries,
                         List<News> news, List<Blog> blogs, List<Company> companies, List<Tool> tools, List<RealWorldApp> realWorldApps, List<Download> downloads,
                         List<Tutorial> tutorials, List<Tip> tips, List<LinksOfTheWeek> links, List<IkonliPack> ikonliPacks) {
        clearData();

        setOpenJFXText(openJFXText);
        setHomeText(homeText);

        getPeople().setAll(people);
        getBooks().setAll(books);
        getVideos().setAll(videos);
        getLibraries().setAll(libraries);
        getNews().setAll(news);
        getBlogs().setAll(blogs);
        getCompanies().setAll(companies);
        getTools().setAll(tools);
        getRealWorldApps().setAll(realWorldApps);
        getDownloads().setAll(downloads);
        getTutorials().setAll(tutorials);
        getTips().setAll(tips);
        getLinksOfTheWeek().setAll(links);
        getIkonliPacks().setAll(ikonliPacks);

        List<ModelObject> recentItems = findRecentItems();
        getRecentItems().setAll(recentItems);
    }

    private List<ModelObject> findRecentItems() {
        List<ModelObject> result = new ArrayList<>();
        // News are not reachable through links!
        //  result.addAll(findRecentItems(getNews()));
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
        result.addAll(findRecentItems(getIkonliPacks()));
        // LinksOfTheWeek are not reachable through links!
        //  result.addAll(findRecentItems(getLinksOfTheWeek()));

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

    private final ObservableList<ModelObject> recentItems = FXCollections.observableArrayList();

    public ObservableList<ModelObject> getRecentItems() {
        return recentItems;
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

    public Optional<IkonliPack> getIkonliPackById(String id) {
        return ikonliPacks.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public <T extends ModelObject> ObservableList<T> getLinkedObjects(ModelObject modelObject, Class<T> clazz) {
        List<T> itemList = getList(clazz);
        List<String> idsList = getIdList(modelObject, clazz);
        ObservableList<T> list = FXCollections.observableArrayList();
        list.setAll(itemList.stream().filter(item -> idsList.contains(item.getId()) || getIdList(item, modelObject.getClass()).contains(modelObject.getId())).collect(Collectors.toList()));
        return list;
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
        } else if (clazz.equals(IkonliPack.class)) {
            return modelObject.getIkonliPackIds();
        }

        throw new IllegalArgumentException("unsupported class type: " + clazz.getSimpleName());
    }

    public <T extends ModelObject> List<T> getList(Class<T> clazz) {
        if (clazz.equals(Video.class)) {
            return (List<T>) videos;
        } else if (clazz.equals(Book.class)) {
            return (List<T>) books;
        } else if (clazz.equals(Library.class)) {
            return (List<T>) libraries;
        } else if (clazz.equals(Tutorial.class)) {
            return (List<T>) tutorials;
        } else if (clazz.equals(Download.class)) {
            return (List<T>) downloads;
        } else if (clazz.equals(Person.class)) {
            return (List<T>) people;
        } else if (clazz.equals(Tool.class)) {
            return (List<T>) tools;
        } else if (clazz.equals(RealWorldApp.class)) {
            return (List<T>) realWorldApps;
        } else if (clazz.equals(News.class)) {
            return (List<T>) news;
        } else if (clazz.equals(Blog.class)) {
            return (List<T>) blogs;
        } else if (clazz.equals(Company.class)) {
            return (List<T>) companies;
        } else if (clazz.equals(Tip.class)) {
            return (List<T>) tips;
        } else if (clazz.equals(LinksOfTheWeek.class)) {
            return (List<T>) linksOfTheWeek;
        } else if (clazz.equals(IkonliPack.class)) {
            return (List<T>) ikonliPacks;
        }

        throw new IllegalArgumentException("unsupported class type: " + clazz.getSimpleName());
    }

    public ModelObject getByID(Class<? extends ModelObject> clz, String id) {
        return getList(clz).stream().filter(item -> item.getId().equals(id)).findFirst().get();
    }

    public ObservableList<Video> getVideosByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, Video.class);
    }

    public ObservableList<Download> getDownloadsByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, Download.class);
    }

    public ObservableList<Book> getBooksByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, Book.class);
    }

    public ObservableList<Tutorial> getTutorialsByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, Tutorial.class);
    }

    public ObservableList<Blog> getBlogsByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, Blog.class);
    }

    public ObservableList<Library> getLibrariesByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, Library.class);
    }

    public ObservableList<Tool> getToolsByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, Tool.class);
    }

    public ObservableList<News> getNewsByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, News.class);
    }

    public ObservableList<Company> getCompaniesByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, Company.class);
    }

    public ObservableList<RealWorldApp> getRealWorldAppsByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, RealWorldApp.class);
    }

    public ObservableList<Person> getPeopleByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, Person.class);
    }

    public ObservableList<Tip> getTipsByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, Tip.class);
    }

    public ObservableList<LinksOfTheWeek> getLinksOfTheWeekByModelObject(ModelObject modelObject) {
        return getLinkedObjects(modelObject, LinksOfTheWeek.class);
    }

    public ObjectProperty<LibraryInfo> libraryInfoProperty(Library library) {
        return libraryInfoMap.computeIfAbsent(library, key -> {
            ObjectProperty<LibraryInfo> infoProperty = new SimpleObjectProperty<>();
            loadLibraryInfo(library, infoProperty);
            return infoProperty;
        });
    }

    private void loadLibraryInfo(Library library, ObjectProperty<LibraryInfo> infoProperty) {
        try {
            String libraryId = library.getId();
            File file = new File(getRepositoryDirectory(), "libraries/" + libraryId + "/info.json");
            try (FileReader reader = new FileReader(file, StandardCharsets.UTF_8)) {
                LibraryInfo result = gson.fromJson(reader, LibraryInfo.class);
                infoProperty.set(result);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public StringProperty newsTextProperty(News news) {
        return newsTextMap.computeIfAbsent(news, key -> {
            StringProperty textProperty = new SimpleStringProperty();
            loadNewsText(news, textProperty);
            return textProperty;
        });
    }

    private void loadNewsText(News news, StringProperty textProperty) {
        String text = loadString(new File(getNewsDirectory(news), "/text.md"));
        textProperty.set(text);
    }

    public StringProperty linksOfTheWeekTextProperty(LinksOfTheWeek links) {
        return linksOfTheWeekReadMeMap.computeIfAbsent(links, key -> {
            StringProperty textProperty = new SimpleStringProperty();
            loadLinksOfTheWeekText(links, textProperty);
            return textProperty;
        });
    }

    public void loadLinksOfTheWeekText(LinksOfTheWeek links, StringProperty textProperty) {
        String text = loadString(new File(getLinksOfTheWeekDirectory(links) + "/readme.md"));
        textProperty.set(text);
    }

    public String getLinksOfTheWeekDirectory(LinksOfTheWeek links) {
        return Paths.get(getRepositoryDirectory().getPath(),
                "links",
                links.getId().substring(0, 4),
                links.getId().substring(0, 7),
                links.getId()).toString();
    }

    public StringProperty tutorialTextProperty(Tutorial tutorial) {
        return tutorialTextMap.computeIfAbsent(tutorial, key -> {
            StringProperty textProperty = new SimpleStringProperty();
            loadTutorialText(tutorial, textProperty);
            return textProperty;
        });
    }

    private void loadTutorialText(Tutorial tutorial, StringProperty textProperty) {
        String text = loadString(new File(getRepositoryDirectory(), "tutorials/" + tutorial.getId() + "/readme.md"));
        textProperty.set(text);
    }

    public StringProperty downloadTextProperty(Download download) {
        return downloadTextMap.computeIfAbsent(download, key -> {
            StringProperty textProperty = new SimpleStringProperty();
            loadDownloadText(download, textProperty);
            return textProperty;
        });
    }

    private void loadDownloadText(Download download, StringProperty textProperty) {
        String text = loadString(new File(getRepositoryDirectory(), "downloads/" + download.getId() + "/readme.md"));
        textProperty.set(text);
    }

    public StringProperty bookTextProperty(Book book) {
        return bookTextMap.computeIfAbsent(book, key -> {
            StringProperty textProperty = new SimpleStringProperty();
            loadBookText(book, textProperty);
            return textProperty;
        });
    }

    private void loadBookText(Book book, StringProperty textProperty) {
        String text = loadString(new File(getRepositoryDirectory(), "books/" + book.getId() + "/readme.md"));
        textProperty.set(text);
    }

    public StringProperty personDescriptionProperty(Person person) {
        return personDescriptionMap.computeIfAbsent(person, key -> {
            StringProperty readmeProperty = new SimpleStringProperty();
            loadPersonDescription(person, readmeProperty);
            return readmeProperty;
        });
    }

    private void loadPersonDescription(Person person, StringProperty readmeProperty) {
        String readmeText = loadString(new File(getRepositoryDirectory(), "people/" + person.getId() + "/readme.md"));
        readmeProperty.set(readmeText);
    }

    public StringProperty toolDescriptionProperty(Tool tool) {
        return toolDescriptionMap.computeIfAbsent(tool, key -> {
            StringProperty readmeProperty = new SimpleStringProperty();
            loadToolDescription(tool, readmeProperty);
            return readmeProperty;
        });
    }

    private void loadToolDescription(Tool tool, StringProperty readmeProperty) {
        String readmeText = loadString(new File(getRepositoryDirectory(), "tools/" + tool.getId() + "/readme.md"));
        readmeProperty.set(readmeText);
    }

    public StringProperty tipDescriptionProperty(Tip tip) {
        return tipDescriptionMap.computeIfAbsent(tip, key -> {
            StringProperty readmeProperty = new SimpleStringProperty();
            loadTipDescription(tip, readmeProperty);
            return readmeProperty;
        });
    }

    private void loadTipDescription(Tip tip, StringProperty readmeProperty) {
        String readmeText = loadString(new File(getRepositoryDirectory(), "tips/" + tip.getId() + "/readme.md"));
        readmeProperty.set(readmeText);
    }

    public StringProperty realWorldAppDescriptionProperty(RealWorldApp app) {
        return realWorldAppDescriptionMap.computeIfAbsent(app, key -> {
            StringProperty readmeProperty = new SimpleStringProperty();
            loadRealWorldDescription(app, readmeProperty);
            return readmeProperty;
        });
    }

    private void loadRealWorldDescription(RealWorldApp app, StringProperty readmeProperty) {
        String readmeText = loadString(new File(getRepositoryDirectory(), "realworld/" + app.getId() + "/readme.md"));
        readmeProperty.set(readmeText);
    }

    public StringProperty companyDescriptionProperty(Company company) {
        return companyDescriptionMap.computeIfAbsent(company, key -> {
            StringProperty readmeProperty = new SimpleStringProperty();
            loadCompanyDescription(company, readmeProperty);
            return readmeProperty;
        });
    }

    private void loadCompanyDescription(Company company, StringProperty readmeProperty) {
        String readmeText = loadString(new File(getRepositoryDirectory(), "companies/" + company.getId() + "/readme.md"));
        readmeProperty.set(readmeText);
    }

    public static void setTesting(boolean testing) {
        DataRepository.testing = testing;
    }

    public File getRepositoryDirectory() {
        if (testing) {
            return new File(System.getProperty("user.dir"));
        }
        return REPO_DIRECTORY;
    }

    public String getRepositoryDirectoryURL() {
        return getRepositoryDirectory().toURI().toString();
    }

    public File getNewsDirectory(News news) {
        return new File(getRepositoryDirectory(), "news/" + DATE_FORMATTER.format(news.getCreatedOn()) + "-" + news.getId());
    }

    public StringProperty libraryReadMeProperty(Library library) {
        return libraryReadMeMap.computeIfAbsent(library, key -> {
            StringProperty readmeProperty = new SimpleStringProperty();
            loadLibraryDescription(library, readmeProperty);
            return readmeProperty;
        });
    }

    private void loadLibraryDescription(Library library, StringProperty readmeProperty) {
        String readmeText = loadString(new File(getRepositoryDirectory(), "libraries/" + library.getId() + "/readme.md"));
        readmeProperty.set(readmeText);
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

    private final ObservableList<Library> libraries = FXCollections.observableArrayList();

    public ObservableList<Library> getLibraries() {
        return libraries;
    }

    private final ObservableList<Blog> blogs = FXCollections.observableArrayList();

    public ObservableList<Blog> getBlogs() {
        return blogs;
    }

    private final ObservableList<News> news = FXCollections.observableArrayList();

    public ObservableList<News> getNews() {
        return news;
    }

    private final ObservableList<Book> books = FXCollections.observableArrayList();

    public ObservableList<Book> getBooks() {
        return books;
    }

    private final ObservableList<LinksOfTheWeek> linksOfTheWeek = FXCollections.observableArrayList();

    public ObservableList<LinksOfTheWeek> getLinksOfTheWeek() {
        return linksOfTheWeek;
    }

    private final ObservableList<Tip> tips = FXCollections.observableArrayList();

    public ObservableList<Tip> getTips() {
        return tips;
    }

    private final ObservableList<Tutorial> tutorials = FXCollections.observableArrayList();

    public ObservableList<Tutorial> getTutorials() {
        return tutorials;
    }

    private final ObservableList<Video> videos = FXCollections.observableArrayList();

    public ObservableList<Video> getVideos() {
        return videos;
    }

    private final ObservableList<Download> downloads = FXCollections.observableArrayList();

    public ObservableList<Download> getDownloads() {
        return downloads;
    }

    private final ObservableList<RealWorldApp> realWorldApps = FXCollections.observableArrayList();

    public ObservableList<RealWorldApp> getRealWorldApps() {
        return realWorldApps;
    }

    private final ObservableList<Tool> tools = FXCollections.observableArrayList();

    public ObservableList<Tool> getTools() {
        return tools;
    }

    private final ObservableList<Company> companies = FXCollections.observableArrayList();

    public ObservableList<Company> getCompanies() {
        return companies;
    }

    private final ObservableList<Person> people = FXCollections.observableArrayList();

    public ObservableList<Person> getPeople() {
        return people;
    }

    private final ObservableList<IkonliPack> ikonliPacks = FXCollections.observableArrayList();

    public ObservableList<IkonliPack> getIkonliPacks() {
        return ikonliPacks;
    }

    private String loadString(File file) {
        LOG.fine("loading string from: " + file);

        StringBuilder sb = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (MalformedURLException e) {
            LOG.fine("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            LOG.fine("I/O Error: " + e.getMessage());
        }

        return sb.toString();
    }

    public StringProperty getArtifactVersion(Coordinates coordinates) {

        String groupId = coordinates.getGroupId();
        String artifactId = coordinates.getArtifactId();

        StringProperty result = new SimpleStringProperty("");

        if (StringUtils.isNotBlank(groupId) && StringUtils.isNotBlank(artifactId)) {
            loadArtifactVersion(groupId, artifactId, result);
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
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    StringBuffer content = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    in.close();

                    QueryResult queryResult = gson.fromJson(content.toString(), QueryResult.class);
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

    public List<Post> loadPosts(Blog blog) {
        LOG.fine("loading posts for blog " + blog.getName());
        try {
            String url = blog.getFeed();
            if (StringUtils.isNotBlank(url)) {
                List<Post> posts = new ArrayList<>();
                URL urlObject = new URL(url);
                URLConnection urlConnection = urlObject.openConnection();
                try (XmlReader reader = new XmlReader(urlConnection.getInputStream())) {
                    SyndFeed feed = new SyndFeedInput().build(reader);
                    List<SyndEntry> entries = feed.getEntries();
                    entries.forEach(entry -> posts.add(new Post(blog, feed, entry)));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return posts;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
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
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                        String inputLine;
                        StringBuffer content = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                            content.append(inputLine);
                        }
                        return gson.fromJson(content.toString(), new TypeToken<List<PullRequest>>() {
                        }.getType());
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
        search(getIkonliPacks(), pattern, result);
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
