package com.dlsc.jfxcentral.data;

import com.dlsc.jfxcentral.data.model.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.net.URLConnection;
import java.time.ZonedDateTime;

public class ImageManager { //extends HashMap<String, ObjectProperty<Image>> {

    private static final Image MISSING_USER_IMAGE = new Image(ImageManager.class.getResource("missing-user.png").toExternalForm());
    private static final Image MISSING_IMAGE = new Image(ImageManager.class.getResource("missing-image.jpg").toExternalForm());
    private static final Image MISSING_VIDEO_IMAGE = new Image(ImageManager.class.getResource("missing-video.png").toExternalForm());

    private static final ImageManager instance = new ImageManager();

    private ImageManager() {
    }

    public static synchronized ImageManager getInstance() {
        return instance;
    }

    public ObjectProperty<Image> newsBannerImageProperty(News news) {
        return localImageProperty(DataRepository.getInstance().getNewsBaseUrl(news) + "/", "banner.jpg", "news-banner-" + news.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> downloadBannerImageProperty(Download download) {
        return localImageProperty(DataRepository.getInstance().getBaseUrl() + "downloads/" + download.getId() + "/", "banner.jpg", "download-banner-" + download.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> blogPageImageProperty(Blog blog) {
        return localImageProperty(DataRepository.getInstance().getBaseUrl() + "blogs/" + blog.getId() + "/", "page-small.png", "blog-page-" + blog.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> blogIconImageProperty(Blog blog) {
        return localImageProperty(DataRepository.getInstance().getBaseUrl() + "blogs/" + blog.getId() + "/", "icon.png", "blog-icon-" + blog.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> realWorldAppImageProperty(RealWorldApp app) {
        return localImageProperty(DataRepository.getInstance().getBaseUrl() + "realworld/" + app.getId() + "/", "small.jpg", "real-" + app.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> realWorldAppLargeImageProperty(RealWorldApp app) {
        return localImageProperty(DataRepository.getInstance().getBaseUrl() + "realworld/" + app.getId() + "/", "large.jpg", "real-large-" + app.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> blogPageLargeImageProperty(Blog blog) {
        return localImageProperty(DataRepository.getInstance().getBaseUrl() + "blogs/" + blog.getId() + "/", "page.png", "blog-large-" + blog.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> personImageProperty(Person person) {
        return localImageProperty(DataRepository.getInstance().getBaseUrl() + "people/" + person.getId() + "/", "photo.jpeg", "person-" + person.getId());
    }

    public ObjectProperty<Image> toolImageProperty(Tool tool) {
        return localImageProperty(DataRepository.getInstance().getBaseUrl() + "tools/" + tool.getId() + "/", "logo.png", "tool-" + tool.getId());
    }

    public ObjectProperty<Image> companyImageProperty(Company company) {
        return localImageProperty(DataRepository.getInstance().getBaseUrl() + "companies/" + company.getId() + "/", "logo.png", "company-" + company.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> tutorialImageProperty(Tutorial tutorial) {
        return localImageProperty(DataRepository.getInstance().getBaseUrl() + "tutorials/" + tutorial.getId() + "/", "small.png", "tutorial-" + tutorial.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> tutorialImageLargeProperty(Tutorial tutorial) {
        return localImageProperty(DataRepository.getInstance().getBaseUrl() + "tutorials/" + tutorial.getId() + "/", "large.png", "tutorial-large-" + tutorial.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> bookCoverImageProperty(Book book) {
        return localImageProperty(DataRepository.getInstance().getBaseUrl() + "books/" + book.getId() + "/", "cover.jpg", "book-" + book.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> libraryImageProperty(Library library) {
        if (library.isLogoAvailable()) {
            return localImageProperty(DataRepository.getInstance().getBaseUrl() + "libraries/" + library.getId() + "/", "logo.png", "library-" + library.getId(), MISSING_IMAGE);
        }

        return new SimpleObjectProperty<>();
    }

    public ObjectProperty<Image> libraryImageProperty(Library library, String imagePath) {
        return localImageProperty(DataRepository.getInstance().getBaseUrl() + "libraries/" + library.getId() + "/", imagePath, imagePath, MISSING_IMAGE);
    }

    public ObjectProperty<Image> youTubeImageProperty(Video video) {
        return remoteImageProperty("https://img.youtube.com/vi/" + video.getId(), "/0.jpg", video.getId(), MISSING_VIDEO_IMAGE);
    }

    public ObjectProperty<Image> githubAvatarImageProperty(String loginName) {
        return remoteImageProperty("https://github.com/", loginName + ".png", "&size=100", "github-" + loginName, MISSING_USER_IMAGE);
    }

    ObjectProperty<Image> localImageProperty(String baseURL, String photoFileName, String photoKey) {
        return localImageProperty(baseURL, photoFileName, photoKey, null);
    }

    ObjectProperty<Image> localImageProperty(String baseURL, String photoFileName, String photoKey, Image placeholderImage) {
        return localImageProperty(baseURL, photoFileName, "", photoKey, placeholderImage);
    }

    ObjectProperty<Image> localImageProperty(String baseURL, String photoFileName, String append, String photoKey, Image placeholderImage) {
        if (StringUtils.isBlank(photoFileName) || StringUtils.isBlank(photoKey)) {
            return new SimpleObjectProperty<>(placeholderImage);
        }

//        return computeIfAbsent(photoKey, key -> {
            ObjectProperty<Image> property = new SimpleObjectProperty<>();

            if (DataRepository.ASYNC) {
                // when unit tests are running we do not want to ever see a placeholder image, because we want to see if the image is missing
                property.set(placeholderImage);
            }

            try {
                URL url = new URL(baseURL + photoFileName);
                System.out.println("loading image from local repository: " + url.toExternalForm());
                Image image = new Image(url.toExternalForm());
                if(image.getException() != null) {
                    throw image.getException();
                }
                property.set(image);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

            return property;
//        });
    }

    private ObjectProperty<Image> remoteImageProperty(String baseURL, String photoFileName, String photoKey, Image placeholderImage) {
        return remoteImageProperty(baseURL, photoFileName, "", photoKey, placeholderImage);
    }

    private ObjectProperty<Image> remoteImageProperty(String baseURL, String photoFileName, String append, String photoKey, Image placeholderImage) {
        if (StringUtils.isBlank(photoFileName) || StringUtils.isBlank(photoKey)) {
            return new SimpleObjectProperty<>(placeholderImage);
        }

//        return computeIfAbsent(photoKey, key -> {
            ObjectProperty<Image> property = new SimpleObjectProperty<>();

            if (DataRepository.ASYNC) {
                // when unit tests are running we do not want to ever see a placeholder image
                property.set(placeholderImage);
            }

            try {
                URL url = new URL(baseURL + photoFileName + "?" + ZonedDateTime.now().toInstant() + append);
                URLConnection connection = url.openConnection();

                System.out.println("loading remote image from url: " + url.toExternalForm());

                if (!DataRepository.ASYNC) {
                    // start using the connection to make sure the file actually exists
                    // but only when we are running our unit tests
                    connection.connect();
                }

                Image image = new Image(url.toExternalForm(), DataRepository.ASYNC);
                image.progressProperty().addListener(it -> {
                    // exception = 404 -> no image found for given URL
                    if (image.getProgress() == 1 && image.getException() == null) {
                        System.out.println("Image found for " + url);
                        property.set(image);
                    }
                });
                image.exceptionProperty().addListener(it -> image.getException().printStackTrace());

                // when running unit tests we set the loaded image immediately
                if (!DataRepository.ASYNC) {
                    property.set(image);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return property;
//        });
    }
}
