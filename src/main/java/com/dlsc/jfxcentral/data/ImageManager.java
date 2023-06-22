package com.dlsc.jfxcentral.data;

import com.dlsc.jfxcentral.data.model.Blog;
import com.dlsc.jfxcentral.data.model.Book;
import com.dlsc.jfxcentral.data.model.Company;
import com.dlsc.jfxcentral.data.model.Download;
import com.dlsc.jfxcentral.data.model.Library;
import com.dlsc.jfxcentral.data.model.Member;
import com.dlsc.jfxcentral.data.model.News;
import com.dlsc.jfxcentral.data.model.Person;
import com.dlsc.jfxcentral.data.model.RealWorldApp;
import com.dlsc.jfxcentral.data.model.Tip;
import com.dlsc.jfxcentral.data.model.Tool;
import com.dlsc.jfxcentral.data.model.Tutorial;
import com.dlsc.jfxcentral.data.model.Video;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ImageManager {

    private static final Logger LOG = Logger.getLogger(ImageManager.class.getName());

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
        return localImageProperty(DataRepository.getInstance().getNewsDirectory(news), "banner.jpg", "news-banner-" + news.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> downloadBannerImageProperty(Download download) {
        return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "downloads/" + download.getId() + "/"), "banner.jpg", "download-banner-" + download.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> tipBannerImageProperty(Tip tip) {
        return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "tips/" + tip.getId() + "/"), "banner.jpg", "tip-banner-" + tip.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> blogPageImageProperty(Blog blog) {
        return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "blogs/" + blog.getId() + "/"), "page-small.png", "blog-page-" + blog.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> blogIconImageProperty(Blog blog) {
        return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "blogs/" + blog.getId() + "/"), "icon.png", "blog-icon-" + blog.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> realWorldAppImageProperty(RealWorldApp app) {
        return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "realworld/" + app.getId() + "/"), "small.jpg", "real-" + app.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> realWorldAppLargeImageProperty(RealWorldApp app) {
        return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "realworld/" + app.getId() + "/"), "large.jpg", "real-large-" + app.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> realWorldAppBannerImageProperty(RealWorldApp app) {
        return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "realworld/" + app.getId() + "/"), "banner.jpg", "real-banner-" + app.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> blogPageLargeImageProperty(Blog blog) {
        return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "blogs/" + blog.getId() + "/"), "page.png", "blog-large-" + blog.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> personImageProperty(Person person) {
        return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "people/" + person.getId() + "/"), "photo.jpeg", "person-" + person.getId());
    }

    public ObjectProperty<Image> memberImageProperty(Member member) {
        return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "members/" + member.getId() + "/"), "photo.jpeg", "member-" + member.getId());
    }

    public ObjectProperty<Image> toolImageProperty(Tool tool) {
        return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "tools/" + tool.getId() + "/"), "logo.png", "tool-" + tool.getId());
    }

    public ObjectProperty<Image> companyImageProperty(Company company) {
        return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "companies/" + company.getId() + "/"), "logo.png", "company-" + company.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> tutorialImageProperty(Tutorial tutorial) {
        return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "tutorials/" + tutorial.getId() + "/"), "small.png", "tutorial-" + tutorial.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> tutorialImageLargeProperty(Tutorial tutorial) {
        return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "tutorials/" + tutorial.getId() + "/"), "large.png", "tutorial-large-" + tutorial.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> bookCoverImageProperty(Book book) {
        return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "books/" + book.getId() + "/"), "cover.jpg", "book-" + book.getId(), MISSING_IMAGE);
    }

    public ObjectProperty<Image> libraryImageProperty(Library library) {
        if (library.isLogoAvailable()) {
            return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "libraries/" + library.getId() + "/"), "logo.png", "library-" + library.getId(), MISSING_IMAGE);
        }

        return new SimpleObjectProperty<>();
    }

    public ObjectProperty<Image> libraryImageProperty(Library library, String imagePath) {
        return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "libraries/" + library.getId() + "/"), imagePath, imagePath, MISSING_IMAGE);
    }

    public ObjectProperty<Image> youTubeImageProperty(Video video) {
        return remoteImageProperty("https://img.youtube.com/vi/" + video.getId(), "/0.jpg", video.getId(), MISSING_VIDEO_IMAGE);
    }

    public ObjectProperty<Image> githubAvatarImageProperty(String loginName) {
        return remoteImageProperty("https://github.com/", loginName + ".png", "&size=100", "github-" + loginName, MISSING_USER_IMAGE);
    }

    ObjectProperty<Image> localImageProperty(File baseURL, String photoFileName, String photoKey) {
        return localImageProperty(baseURL, photoFileName, photoKey, null);
    }

    ObjectProperty<Image> localImageProperty(File baseURL, String photoFileName, String photoKey, Image placeholderImage) {
        return localImageProperty(baseURL, photoFileName, "", photoKey, placeholderImage);
    }

    ObjectProperty<Image> localImageProperty(File directory, String photoFileName, String append, String photoKey, Image placeholderImage) {
        if (StringUtils.isBlank(photoFileName) || StringUtils.isBlank(photoKey)) {
            return new SimpleObjectProperty<>(placeholderImage);
        }

        ObjectProperty<Image> property = new SimpleObjectProperty<>();

        File file = new File(directory, photoFileName);
        try (FileInputStream in = new FileInputStream(file)) {
            LOG.fine("loading image from local repository: " + file.toURI().toURL().toExternalForm());
            Image image = new Image(in);
            if (image.getException() != null) {
                throw image.getException();
            }
            property.set(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return property;
    }

    private Map<String, ObjectProperty<Image>> remoteImageCache = new HashMap<>();

    private ObjectProperty<Image> remoteImageProperty(String baseURL, String photoFileName, String photoKey, Image placeholderImage) {
        return remoteImageProperty(baseURL, photoFileName, "", photoKey, placeholderImage);
    }

    private ObjectProperty<Image> remoteImageProperty(String baseURL, String photoFileName, String append, String photoKey, Image placeholderImage) {
        if (StringUtils.isBlank(photoFileName) || StringUtils.isBlank(photoKey)) {
            return new SimpleObjectProperty<>(placeholderImage);
        }

        return remoteImageCache.computeIfAbsent(photoKey, key -> {
            ObjectProperty<Image> property = new SimpleObjectProperty<>();

            try {
                URL url = new URL(baseURL + photoFileName + "?" + ZonedDateTime.now().toInstant() + append);
                URLConnection connection = url.openConnection();

                LOG.fine("loading remote image from url: " + url.toExternalForm());

                connection.connect();

                Image image = new Image(url.toExternalForm(), false);
                image.progressProperty().addListener(it -> {
                    // exception = 404 -> no image found for given URL
                    if (image.getProgress() == 1 && image.getException() == null) {
                        LOG.fine("Image found for " + url);
                        property.set(image);
                    }
                });
                image.exceptionProperty().addListener(it -> image.getException().printStackTrace());

                // when running unit tests we set the loaded image immediately
                property.set(image);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return property;
        });
    }
}
