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
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
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

    private Map<Download, StringProperty> downloadTextMap = new HashMap<>();

    private Map<Book, StringProperty> bookTextMap = new HashMap<>();

    private Map<Person, StringProperty> personDescriptionMap = new HashMap<>();

    private Map<Tool, StringProperty> toolDescriptionMap = new HashMap<>();

    private Map<RealWorldApp, StringProperty> realWorldAppDescriptionMap = new HashMap<>();

    private Map<Company, StringProperty> companyDescriptionMap = new HashMap<>();

    private Map<Library, StringProperty> libraryReadMeMap = new HashMap<>();

    public static synchronized DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }

        return instance;
    }

    private DataRepository() {
        recentItems.addListener((Observable it) -> System.out.println("recent items count: " + getRecentItems().size()));
        loadData();
        sourceProperty().addListener(it -> refreshData());
    }

    public void refreshData() {
        clearData();
        loadData();
    }

    public void clearData() {
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
    }

    private void loadData() {
        try {
            double steps = 14d;
            setProgress(0);
            setMessage("");

            setMessage("Loading text for start page");
            setHomeText(loadString(getBaseUrl() + "intro.md?time=" + ZonedDateTime.now().toInstant()));
            setProgress(getProgress() + 1 / steps);

            setMessage("Loading text for OpenJFX project");
            setOpenJFXText(loadString(getBaseUrl() + "openjfx/intro.md?time=" + ZonedDateTime.now().toInstant()));
            setProgress(getProgress() + 1 / steps);

            // load people
            setMessage("Loading index of people");
            File peopleFile = loadFile("people", getBaseUrl() + "people/people.json");
            setPeople(gson.fromJson(new FileReader(peopleFile), new TypeToken<List<Person>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            // load books
            setMessage("Loading index of books");
            File booksFile = loadFile("books", getBaseUrl() + "books/books.json");
            setBooks(gson.fromJson(new FileReader(booksFile), new TypeToken<List<Book>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            // load videos
            setMessage("Loading index of videos");
            File videosFile = loadFile("videos", getBaseUrl() + "videos/videos.json");
            setVideos(gson.fromJson(new FileReader(videosFile), new TypeToken<List<Video>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            // load libraries
            setMessage("Loading index of libraries");
            File librariesFile = loadFile("libraries", getBaseUrl() + "libraries/libraries.json");
            setLibraries(gson.fromJson(new FileReader(librariesFile), new TypeToken<List<Library>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            // load libraries
            setMessage("Loading index of news");
            File newsFile = loadFile("news", getBaseUrl() + "news/news.json");
            setNews(gson.fromJson(new FileReader(newsFile), new TypeToken<List<News>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            // load libraries
            setMessage("Loading index of blogs");
            File blogsFile = loadFile("blogs", getBaseUrl() + "blogs/blogs.json");
            setBlogs(gson.fromJson(new FileReader(blogsFile), new TypeToken<List<Blog>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            // load libraries
            setMessage("Loading index of companies");
            File companiesFile = loadFile("companies", getBaseUrl() + "companies/companies.json");
            setCompanies(gson.fromJson(new FileReader(companiesFile), new TypeToken<List<Company>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            // load tools
            setMessage("Loading index of tools");
            File toolsFile = loadFile("tools", getBaseUrl() + "tools/tools.json");
            setTools(gson.fromJson(new FileReader(toolsFile), new TypeToken<List<Tool>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            // load real world apps
            setMessage("Loading index of real world apps");
            File realWorldFile = loadFile("realworld", getBaseUrl() + "realworld/realworld.json");
            setRealWorldApps(gson.fromJson(new FileReader(realWorldFile), new TypeToken<List<RealWorldApp>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            // load downloads
            setMessage("Loading index of downloads");
            File downloadsFile = loadFile("downloads", getBaseUrl() + "downloads/downloads.json");
            setDownloads(gson.fromJson(new FileReader(downloadsFile), new TypeToken<List<Download>>() {
            }.getType()));
            setProgress(getProgress() + 1 / steps);

            if (!Boolean.getBoolean("no.feeds")) {
                setMessage("Loading blog feeds");
                readFeeds();
            }
            setProgress(getProgress() + 1 / steps);

            setMessage("Loading pull requests from OpenJFX project.");
            loadPullRequests();
            setProgress(getProgress() + 1 / steps);

            setMessage("Updating list of recent items");
            updateRecentItems();
            setProgress(getProgress() + 1 / steps);

        } catch (Exception e) {
            e.printStackTrace();
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
                if (date.isAfter(today.minusWeeks(2))) {
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

    public ListProperty<Video> getVideosByPerson(Person person) {
        ListProperty<Video> listProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
        listProperty.setAll(videos.stream().filter(video -> video.getPersonIds().contains(person.getId())).collect(Collectors.toList()));
        return listProperty;
    }

    public ListProperty<Blog> getBlogsByPerson(Person person) {
        ListProperty<Blog> listProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
        listProperty.setAll(blogs.stream().filter(blog -> blog.getPersonIds().contains(person.getId())).collect(Collectors.toList()));
        return listProperty;
    }

    public ListProperty<Library> getLibrariesByPerson(Person person) {
        List<Library> result = libraries.stream().filter(library -> library.getPersonId().equals(person.getId())).collect(Collectors.toList());
        return new SimpleListProperty<>(FXCollections.observableArrayList(result));
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
            File file = loadFile(libraryId + ".json", getBaseUrl() + "libraries/" + libraryId + "/_info.json?time=" + ZonedDateTime.now().toInstant());
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
        String url = getNewsBaseUrl(news) + "/text.md?time=" + ZonedDateTime.now().toInstant();
        System.out.println("loading news from: " + url);
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
        String url = getBaseUrl() + "downloads/" + download.getId() + "/readme.md?time=" + ZonedDateTime.now().toInstant();
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
        String url = getBaseUrl() + "books/" + book.getId() + "/readme.md?time=" + ZonedDateTime.now().toInstant();
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
        if (BASE_URL.startsWith("file://")) { // for local tests
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
        String readmeText = loadString(getBaseUrl() + "libraries/" + library.getId() + "/_readme.md");
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

    public ListProperty<Book> getBooksByPerson(Person person) {
        ObservableList<Book> list = FXCollections.observableArrayList();
        list.setAll(getBooks().stream().filter(book -> book.getPersonIds().contains(person.getId())).collect(Collectors.toList()));
        return new SimpleListProperty<>(list);
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
    }

    private final ListProperty<Person> people = new SimpleListProperty<>(this, "people", FXCollections.observableArrayList());

    public ObservableList<Person> getPeople() {
        return people.get();
    }

    public ListProperty<Person> peopleProperty() {
        return people;
    }

    public void setPeople(List<Person> people) {
        System.out.println("PEOPLE SIZE: " + people.size());
        this.people.setAll(people);
    }

    private File loadFile(String fileName, String urlString) throws IOException {
        // adding caching buster via timestamp
        urlString = urlString + "?time=" + ZonedDateTime.now().toInstant();
        System.out.println("url: " + urlString);

        URL url = new URL(urlString);

        URLConnection connection = url.openConnection();
        connection.setUseCaches(false);
        connection.setDefaultUseCaches(false);

        ReadableByteChannel readChannel = Channels.newChannel(connection.getInputStream());
        File file = File.createTempFile(fileName, ".json");
        System.out.println("file: " + file.getAbsolutePath());
        FileOutputStream fileOS = new FileOutputStream(file);
        FileChannel writeChannel = fileOS.getChannel();
        writeChannel.transferFrom(readChannel, 0, Long.MAX_VALUE);

        return file;
    }

    private String loadString(String address) {
        System.out.println("loading string from: " + address);

        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(address);

            URLConnection connection = url.openConnection();
            connection.setUseCaches(false);

            // read text returned by server
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

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

    public StringProperty getArtifactVersion(Library library) {

        String groupId = library.getGroupId();
        String artifactId = library.getArtifactId();

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

    public void readFeeds() throws IOException, FeedException {
        ObservableList<Blog> blogObservableList = getBlogs();
        int size = blogObservableList.size();

        for (int i = 0; i < size; i++) {
            Blog blog = blogObservableList.get(i);
            String url = blog.getFeed();
            if (StringUtils.isNotBlank(url)) {

                System.out.println("loading blog posts from url: " + url);
                SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));

                List<SyndEntry> entries = feed.getEntries();
                entries.forEach(entry -> getPosts().add(new Post(blog, feed, entry)));

                if (!ASYNC && !entries.isEmpty()) {
                    // to save time when unit tests are running
                    break;
                }
            }
        }
    }

    public void loadPullRequests() {
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
