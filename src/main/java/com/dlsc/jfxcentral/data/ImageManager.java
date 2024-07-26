package com.dlsc.jfxcentral.data;

import com.dlsc.jfxcentral.data.model.Blog;
import com.dlsc.jfxcentral.data.model.Book;
import com.dlsc.jfxcentral.data.model.Company;
import com.dlsc.jfxcentral.data.model.Documentation;
import com.dlsc.jfxcentral.data.model.Download;
import com.dlsc.jfxcentral.data.model.Library;
import com.dlsc.jfxcentral.data.model.Member;
import com.dlsc.jfxcentral.data.model.News;
import com.dlsc.jfxcentral.data.model.Utility;
import com.dlsc.jfxcentral.data.model.Person;
import com.dlsc.jfxcentral.data.model.RealWorldApp;
import com.dlsc.jfxcentral.data.model.Tip;
import com.dlsc.jfxcentral.data.model.Tool;
import com.dlsc.jfxcentral.data.model.Tutorial;
import com.dlsc.jfxcentral.data.model.Video;
import com.jpro.webapi.WebAPI;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class ImageManager {

    private static final Logger LOG = Logger.getLogger(ImageManager.class.getName());

    private static final String MISSING_USER = Objects.requireNonNull(ImageManager.class.getResource("missing-user.png")).toExternalForm();

    private static final Image MISSING_USER_IMAGE = new Image(MISSING_USER);

    private static final String MISSING = Objects.requireNonNull(ImageManager.class.getResource("missing-image.jpg")).toExternalForm();

    private static final Image MISSING_IMAGE = new Image(MISSING);

    private static final String MISSING_VIDEO = Objects.requireNonNull(ImageManager.class.getResource("missing-video.png")).toExternalForm();

    private static final Image MISSING_VIDEO_IMAGE = new Image(MISSING_VIDEO);

    private static final ImageManager instance = new ImageManager();

    private ImageManager() {
    }

    public static synchronized ImageManager getInstance() {
        return instance;
    }

    public File newsBannerFile(News news) {
        return localImageFile(DataRepository.getInstance().getNewsDirectory(news), "banner.jpg");
    }

    public File downloadBannerFile(Download download) {
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "downloads/" + download.getId() + "/"), "banner.jpg");
    }

    public File tipBannerFile(Tip tip) {
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "tips/" + tip.getId() + "/"), "banner.jpg");
    }

    public File blogPageFile(Blog blog) {
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "blogs/" + blog.getId() + "/"), "page-small.png");
    }

    public File blogIconFile(Blog blog) {
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "blogs/" + blog.getId() + "/"), "icon.png");
    }

    public File realWorldAppFile(RealWorldApp app) {
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "realworld/" + app.getId() + "/"), "small.jpg");
    }

    public File realWorldAppLargeFile(RealWorldApp app) {
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "realworld/" + app.getId() + "/"), "large.jpg");
    }

    public File realWorldAppBannerFile(RealWorldApp app) {
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "realworld/" + app.getId() + "/"), "banner.jpg");
    }

    public File blogPageLargeFile(Blog blog) {
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "blogs/" + blog.getId() + "/"), "page.png");
    }

    public File personFile(Person person) {
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "people/" + person.getId() + "/"), "photo.jpeg");
    }

    public File memberFile(Member member) {
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "members/" + member.getId() + "/"), "photo.jpeg");
    }

    public File toolFile(Tool tool) {
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "tools/" + tool.getId() + "/"), "logo.png");
    }

    public File utilityFile(Utility utility) {
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "utilities/" + utility.getId() + "/"), "logo.png");
    }

    public File companyFile(Company company) {
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "companies/" + company.getId() + "/"), "logo.png");
    }

    public File tutorialFile(Tutorial tutorial) {
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "tutorials/" + tutorial.getId() + "/"), "small.png");
    }

    public File tutorialLargeFile(Tutorial tutorial) {
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "tutorials/" + tutorial.getId() + "/"), "large.png");
    }

    public File bookCoverFile(Book book) {
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "books/" + book.getId() + "/"), "cover.jpg");
    }

    public File libraryFile(Library library) {
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "libraries/" + library.getId() + "/"), "logo.png");
    }

    public File documentationFile(Documentation doc) {
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "documentation/" + doc.getId() + "/"), "logo.png");
    }

    private File localImageFile(File directory, String photoFileName) {
        if (StringUtils.isBlank(photoFileName)) {
            return null;
        }
        return new File(directory, photoFileName);
    }

    public File libraryFile(Library library, String imagePath) {
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "libraries/" + library.getId() + "/"), imagePath);
    }

    public ObjectProperty<Image> newsBannerImageProperty(News news) {
        File file = newsBannerFile(news);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> downloadBannerImageProperty(Download download) {
        File file = downloadBannerFile(download);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> tipBannerImageProperty(Tip tip) {
        File file = tipBannerFile(tip);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> blogPageImageProperty(Blog blog) {
        File file = blogPageFile(blog);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> blogIconImageProperty(Blog blog) {
        File file = blogIconFile(blog);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> realWorldAppImageProperty(RealWorldApp app) {
        File file = realWorldAppFile(app);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> realWorldAppLargeImageProperty(RealWorldApp app) {
        File file = realWorldAppLargeFile(app);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> realWorldAppBannerImageProperty(RealWorldApp app) {
        File file = realWorldAppBannerFile(app);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> blogPageLargeImageProperty(Blog blog) {
        File file = blogPageLargeFile(blog);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> personImageProperty(Person person) {
        File file = personFile(person);
        return fileToImageProperty(file, null);
    }

    public ObjectProperty<Image> memberImageProperty(Member member) {
        File file = memberFile(member);
        return fileToImageProperty(file, null);
    }

    public ObjectProperty<Image> toolImageProperty(Tool tool) {
        File file = toolFile(tool);
        return fileToImageProperty(file, null);
    }

    public ObjectProperty<Image> utilityImageProperty(Utility utility) {
        File file = utilityFile(utility);
        return fileToImageProperty(file, null);
    }

    public ObjectProperty<Image> companyImageProperty(Company company) {
        File file = companyFile(company);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> tutorialImageProperty(Tutorial tutorial) {
        File file = tutorialFile(tutorial);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> tutorialImageLargeProperty(Tutorial tutorial) {
        File file = tutorialLargeFile(tutorial);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> bookCoverImageProperty(Book book) {
        File file = bookCoverFile(book);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> libraryImageProperty(Library library) {
        if (!library.isLogoAvailable()) {
            // original behaviour
            return new SimpleObjectProperty<>();
        }
        File file = libraryFile(library);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> libraryImageProperty(Library library, String imagePath) {
        File file = libraryFile(library, imagePath);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> libraryFeaturedImageProperty(Library library) {
        if (StringUtils.isBlank(library.getFeaturedImageName())) {
            return new SimpleObjectProperty<>();
        }
        return libraryImageProperty(library, library.getFeaturedImageName());
    }

    public ObjectProperty<Image> documentationImageProperty(Documentation doc) {
        File file = documentationFile(doc);
        return fileToImageProperty(file, null);
    }

    public ObjectProperty<Image> fileToImageProperty(File file, Image placeholderImage) {
        ObjectProperty<Image> property = new SimpleObjectProperty<>();

        if (file != null && file.exists()) {
            try {
                Image image = new Image(file.toURI().toString());
                if (image.getException() != null) {
                    throw image.getException();
                }
                property.set(image);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } else {
            if (placeholderImage == null) {
                throw new RuntimeException("Image not found: " + file.getAbsolutePath());
            }
            property.set(placeholderImage);
        }

        return property;
    }

    /*
     * REMOTE IMAGE SUPPORT.
     */

    public String youTubeImageURL(Video video) {
        return "https://img.youtube.com/vi/" + video.getId() + "/0.jpg";
    }

    public ObjectProperty<Image> youTubeImageProperty(Video video) {
        // 480 x 360
        return remoteImageProperty(youTubeImageURL(video), video.getId(), MISSING_VIDEO_IMAGE, 480, 360);
    }

    public ObjectProperty<Image> githubAvatarImageProperty(String loginName) {
        return remoteImageProperty("https://github.com/" + loginName + ".png", "&size=100", "github-" + loginName, MISSING_USER_IMAGE, 100, 100);
    }

    private Map<String, ObjectProperty<Image>> remoteImageCache = new HashMap<>();

    private ObjectProperty<Image> remoteImageProperty(String baseURL, String photoKey, Image placeholderImage, int width, int height) {
        return remoteImageProperty(baseURL, "", photoKey, placeholderImage, width, height);
    }

    private ObjectProperty<Image> remoteImageProperty(String imageUrl, String append, String photoKey, Image placeholderImage, int width, int height) {

        if (WebAPI.isBrowser()) {
            return new SimpleObjectProperty<>(WebAPI.createVirtualImage(imageUrl, width, height));
        }

        if (StringUtils.isBlank(imageUrl) || StringUtils.isBlank(photoKey)) {
            return new SimpleObjectProperty<>(placeholderImage);
        }

        return remoteImageCache.computeIfAbsent(photoKey, key -> {
            ObjectProperty<Image> property = new SimpleObjectProperty<>();

            try {
                URL url = new URL(imageUrl + "?" + ZonedDateTime.now().toInstant() + append);
                URLConnection connection = url.openConnection();

                LOG.fine("loading remote image from url: " + url.toExternalForm());

                connection.connect();

                Image image = new Image(url.toExternalForm(), !DataRepository.testing);
                image.progressProperty().addListener(it -> {
                    // exception = 404 -> no image found for given URL
                    if (image.getProgress() == 1 && image.getException() == null) {
                        LOG.fine("Image found for " + url);
                        property.set(image);
                    }
                });
                image.exceptionProperty().addListener(it -> image.getException().printStackTrace());

                // when running unit tests we set the loaded image immediately
                if (DataRepository.testing) {
                    property.set(image);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return property;
        });
    }
}
