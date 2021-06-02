package com.dlsc.jfxcentral.data;

import com.dlsc.jfxcentral.data.model.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.net.URLConnection;
import java.time.ZonedDateTime;
import java.util.HashMap;

public class ImageManager extends HashMap<String, ObjectProperty<Image>> {

    private static final Image USER_IMAGE = new Image(ImageManager.class.getResource("missing-user.png").toExternalForm());
    private static final Image MISSING_IMAGE = new Image(ImageManager.class.getResource("missing-image.jpg").toExternalForm());
    private static final Image MISSING_VIDEO_IMAGE = new Image(ImageManager.class.getResource("missing-video.png").toExternalForm());

    private static final ImageManager instance = new ImageManager();

    private ImageManager() {
    }

    public static synchronized ImageManager getInstance() {
        return instance;
    }

    public ObjectProperty<Image> newsBannerImageProperty(News news) {
        return imageProperty(DataRepository.getInstance().getNewsBaseUrl(news) + "/", "banner.jpg", "news-banner-" + news.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> downloadBannerImageProperty(Download download) {
        return imageProperty(DataRepository.getInstance().getBaseUrl() + "downloads/" + download.getId() + "/", "banner.jpg", "download-banner-" + download.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> blogPageImageProperty(Blog blog) {
        return imageProperty(DataRepository.getInstance().getBaseUrl() + "blogs/" + blog.getId() + "/", "page-small.png", "blog-" + blog.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> realWorldAppImageProperty(RealWorldApp app) {
        return imageProperty(DataRepository.getInstance().getBaseUrl() + "realworld/" + app.getId() + "/", "small.jpg", "real-" + app.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> realWorldAppLargeImageProperty(RealWorldApp app) {
        return imageProperty(DataRepository.getInstance().getBaseUrl() + "realworld/" + app.getId() + "/", "large.jpg", "real-large-" + app.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> blogPageLargeImageProperty(Blog blog) {
        return imageProperty(DataRepository.getInstance().getBaseUrl() + "blogs/" + blog.getId() + "/", "page.png", "blog-large-" + blog.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> personImageProperty(Person person) {
        return imageProperty(DataRepository.getInstance().getBaseUrl() + "people/" + person.getId() + "/", "photo.jpeg", "person-" + person.getId());
    }

    public ObjectProperty<Image> toolImageProperty(Tool tool) {
        return imageProperty(DataRepository.getInstance().getBaseUrl() + "tools/" + tool.getId() + "/", "logo.png", "tool-" + tool.getId());
    }

    public ObjectProperty<Image> companyImageProperty(Company company) {
        return imageProperty(DataRepository.getInstance().getBaseUrl() + "companies/" + company.getId() + "/", "logo.png", "company-" + company.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> bookCoverImageProperty(Book book) {
        return imageProperty(DataRepository.getInstance().getBaseUrl() + "images/books/", book.getImage());
    }

    public ObjectProperty<Image> libraryImageProperty(Library library) {
        return imageProperty(DataRepository.getInstance().getBaseUrl() + "libraries/" + library.getId() + "/", library.getLogoImageFile());
    }

    public ObjectProperty<Image> libraryImageProperty(Library library, String imagePath) {
        return imageProperty(DataRepository.getInstance().getBaseUrl() + "libraries/" + library.getId() + "/", imagePath, imagePath, MISSING_IMAGE);
    }

    public ObjectProperty<Image> youTubeImageProperty(Video video) {
        return imageProperty("https://img.youtube.com/vi/" + video.getId(), "/0.jpg", video.getId(), MISSING_VIDEO_IMAGE);
    }

    private ObjectProperty<Image> imageProperty(String baseURL, String photoFileName) {
        return imageProperty(baseURL, photoFileName, photoFileName, null);
    }

    private ObjectProperty<Image> imageProperty(String baseURL, String photoFileName, String photoKey) {
        return imageProperty(baseURL, photoFileName, photoKey, null);
    }

    private ObjectProperty<Image> imageProperty(String baseURL, String photoFileName, String photoKey, Image placeholderImage) {
        if (StringUtils.isBlank(photoFileName) || StringUtils.isBlank(photoKey)) {
            return new SimpleObjectProperty<>(placeholderImage);
        }

        return computeIfAbsent(photoKey, key -> {
            ObjectProperty<Image> property = new SimpleObjectProperty<>();

            if (DataRepository.ASYNC) {
                // when unit tests are running we do not want to ever see a placeholder image
                property.set(placeholderImage);
            }

            try {
                URL url = new URL(baseURL + photoFileName + "?" + ZonedDateTime.now().toInstant());
                URLConnection connection = url.openConnection();

                System.out.println("loading image: " + url.toExternalForm());

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
        });
    }
}
