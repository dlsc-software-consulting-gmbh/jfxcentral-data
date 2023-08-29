package com.dlsc.jfxcentral.data;

import com.dlsc.jfxcentral.data.model.Blog;
import com.dlsc.jfxcentral.data.model.Book;
import com.dlsc.jfxcentral.data.model.Company;
import com.dlsc.jfxcentral.data.model.Documentation;
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
import java.util.logging.Logger;

public class ImageManager {

    private static final Logger LOG = Logger.getLogger(ImageManager.class.getName());


    private static final String MISSING_USER = ImageManager.class.getResource("missing-user.png").toExternalForm();

    private static final Image MISSING_USER_IMAGE = new Image(MISSING_USER);

    private static final String MISSING = ImageManager.class.getResource("missing-image.jpg").toExternalForm();

    private static final Image MISSING_IMAGE = new Image(MISSING);

    private static final String MISSING_VIDEO = ImageManager.class.getResource("missing-video.png").toExternalForm();

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
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "blogs/" + blog.getId() + "/"), "page-small.png", "blog-page-" + blog.getId(), MISSING_IMAGE);
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "blogs/" + blog.getId() + "/"), "page-small.png");
    }

    public File blogIconFile(Blog blog) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "blogs/" + blog.getId() + "/"), "icon.png", "blog-icon-" + blog.getId(), MISSING_IMAGE);
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "blogs/" + blog.getId() + "/"), "icon.png");
    }

    public File realWorldAppFile(RealWorldApp app) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "realworld/" + app.getId() + "/"), "small.jpg", "real-" + app.getId(), MISSING_IMAGE);
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "realworld/" + app.getId() + "/"), "small.jpg");
    }

    public File realWorldAppLargeFile(RealWorldApp app) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "realworld/" + app.getId() + "/"), "large.jpg", "real-large-" + app.getId(), MISSING_IMAGE);
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "realworld/" + app.getId() + "/"), "large.jpg");
    }

    public File realWorldAppBannerFile(RealWorldApp app) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "realworld/" + app.getId() + "/"), "banner.jpg", "real-banner-" + app.getId(), MISSING_IMAGE);
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "realworld/" + app.getId() + "/"), "banner.jpg");
    }

    public File blogPageLargeFile(Blog blog) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "blogs/" + blog.getId() + "/"), "page.png", "blog-large-" + blog.getId(), MISSING_IMAGE);
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "blogs/" + blog.getId() + "/"), "page.png");
    }

    public File personFile(Person person) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "people/" + person.getId() + "/"), "photo.jpeg", "person-" + person.getId());
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "people/" + person.getId() + "/"), "photo.jpeg");
    }

    public File memberFile(Member member) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "members/" + member.getId() + "/"), "photo.jpeg", "member-" + member.getId());
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "members/" + member.getId() + "/"), "photo.jpeg");
    }

    public File toolFile(Tool tool) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "tools/" + tool.getId() + "/"), "logo.png", "tool-" + tool.getId());
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "tools/" + tool.getId() + "/"), "logo.png");
    }

    public File companyFile(Company company) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "companies/" + company.getId() + "/"), "logo.png", "company-" + company.getId(), MISSING_IMAGE);
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "companies/" + company.getId() + "/"), "logo.png");
    }

    public File tutorialFile(Tutorial tutorial) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "tutorials/" + tutorial.getId() + "/"), "small.png", "tutorial-" + tutorial.getId(), MISSING_IMAGE);
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "tutorials/" + tutorial.getId() + "/"), "small.png");
    }

    public File tutorialLargeFile(Tutorial tutorial) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "tutorials/" + tutorial.getId() + "/"), "large.png", "tutorial-large-" + tutorial.getId(), MISSING_IMAGE);
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "tutorials/" + tutorial.getId() + "/"), "large.png");
    }

    public File bookCoverFile(Book book) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "books/" + book.getId() + "/"), "cover.jpg", "book-" + book.getId(), MISSING_IMAGE);
        return localImageFile(new File(DataRepository.getInstance().getRepositoryDirectory(), "books/" + book.getId() + "/"), "cover.jpg");
    }

    public File libraryFile(Library library) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "libraries/" + library.getId() + "/"), "logo.png", "library-" + library.getId(), MISSING_IMAGE);
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
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "libraries/" + library.getId() + "/"), imagePath, imagePath, MISSING_IMAGE);
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
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "blogs/" + blog.getId() + "/"), "icon.png", "blog-icon-" + blog.getId(), MISSING_IMAGE);
        File file = blogIconFile(blog);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> realWorldAppImageProperty(RealWorldApp app) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "realworld/" + app.getId() + "/"), "small.jpg", "real-" + app.getId(), MISSING_IMAGE);
        File file = realWorldAppFile(app);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> realWorldAppLargeImageProperty(RealWorldApp app) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "realworld/" + app.getId() + "/"), "large.jpg", "real-large-" + app.getId(), MISSING_IMAGE);
        File file = realWorldAppLargeFile(app);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> realWorldAppBannerImageProperty(RealWorldApp app) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "realworld/" + app.getId() + "/"), "banner.jpg", "real-banner-" + app.getId(), MISSING_IMAGE);
        File file = realWorldAppBannerFile(app);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> blogPageLargeImageProperty(Blog blog) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "blogs/" + blog.getId() + "/"), "page.png", "blog-large-" + blog.getId(), MISSING_IMAGE);
        File file = blogPageLargeFile(blog);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> personImageProperty(Person person) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "people/" + person.getId() + "/"), "photo.jpeg", "person-" + person.getId());
        File file = personFile(person);
        return fileToImageProperty(file, null);
    }

    public ObjectProperty<Image> memberImageProperty(Member member) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "members/" + member.getId() + "/"), "photo.jpeg", "member-" + member.getId());
        File file = memberFile(member);
        return fileToImageProperty(file, null);
    }

    public ObjectProperty<Image> toolImageProperty(Tool tool) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "tools/" + tool.getId() + "/"), "logo.png", "tool-" + tool.getId());
        File file = toolFile(tool);
        return fileToImageProperty(file, null);
    }

    public ObjectProperty<Image> companyImageProperty(Company company) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "companies/" + company.getId() + "/"), "logo.png", "company-" + company.getId(), MISSING_IMAGE);
        File file = companyFile(company);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> tutorialImageProperty(Tutorial tutorial) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "tutorials/" + tutorial.getId() + "/"), "small.png", "tutorial-" + tutorial.getId(), MISSING_IMAGE);
        File file = tutorialFile(tutorial);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> tutorialImageLargeProperty(Tutorial tutorial) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "tutorials/" + tutorial.getId() + "/"), "large.png", "tutorial-large-" + tutorial.getId(), MISSING_IMAGE);
        File file = tutorialLargeFile(tutorial);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> bookCoverImageProperty(Book book) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "books/" + book.getId() + "/"), "cover.jpg", "book-" + book.getId(), MISSING_IMAGE);
        File file = bookCoverFile(book);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> libraryImageProperty(Library library) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "libraries/" + library.getId() + "/"), "logo.png", "library-" + library.getId(), MISSING_IMAGE);
        if (!library.isLogoAvailable()) {
            // original behaviour
            return new SimpleObjectProperty<>();
        }
        File file = libraryFile(library);
        return fileToImageProperty(file, MISSING_IMAGE);
    }

    public ObjectProperty<Image> libraryImageProperty(Library library, String imagePath) {
        //return localImageProperty(new File(DataRepository.getInstance().getRepositoryDirectory(), "libraries/" + library.getId() + "/"), imagePath, imagePath, MISSING_IMAGE);
        File file = libraryFile(library, imagePath);
        return fileToImageProperty(file, MISSING_IMAGE);
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
            if(placeholderImage == null) {
                throw new RuntimeException("Image not found: " + file.getAbsolutePath());
            }
            property.set(placeholderImage);
        }

        return property;
    }




    public String youTubeImageURL(Video video) {
        return "https://img.youtube.com/vi/" + video.getId() + "/0.jpg";
    }

    public ObjectProperty<Image> youTubeImageProperty(Video video) {
        // 480 x 360
        return remoteImageProperty(youTubeImageURL(video), video.getId(), MISSING_VIDEO_IMAGE, 480, 360);
    }


    public ObjectProperty<Image> githubAvatarImageProperty(String loginName) {
        return remoteImageProperty("https://github.com/" + loginName + ".png","&size=100", "github-" + loginName, MISSING_USER_IMAGE, 100, 100);
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
                if(DataRepository.testing) {
                    property.set(image);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return property;
        });
    }

    /*


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

*/
}
