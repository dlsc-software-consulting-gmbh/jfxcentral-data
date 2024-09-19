package com.dlsc.jfxcentral.data;

import com.dlsc.jfxcentral.data.pull.PullRequest;
import com.dlsc.jfxcentral.data.pull.User;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests the existence of the MANDATORY images, e.g. news banner, library icon, etc...
 * Some model objects have OPTIONAL images, e.g. a "person".
 */
@ExtendWith(ApplicationExtension.class)
public class ImageManagerTest {

    @BeforeAll
    public static void setup() {
        Logger packageLogger = Logger.getLogger("com.dlsc.jfxcentral.data");
        packageLogger.setLevel(Level.FINE);

        Logger rootLogger = Logger.getLogger("");
        for (Handler handler : rootLogger.getHandlers()) {
            handler.setLevel(Level.FINE);
        }

        DataRepository2.setTesting(true);
    }

    @Test
    public void shouldGetPersonImage() {
        // when .. then
        DataRepository2.getInstance().getPeople().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().personImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetNewsBanner() {
        // when .. then
        DataRepository2.getInstance().getNews().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().newsBannerImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetDownloadBanner() {
        // when .. then
        DataRepository2.getInstance().getDownloads().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().downloadBannerImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetTipBanner() {
        // when .. then
        DataRepository2.getInstance().getTips().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().tipBannerImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetBlogPageImage() {
        // when .. then
        DataRepository2.getInstance().getBlogs().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().blogPageImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetBlogIconImage() {
        // when .. then
        DataRepository2.getInstance().getBlogs().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().blogIconImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetBlogPageLargeImage() {
        // when .. then
        DataRepository2.getInstance().getBlogs().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().blogPageLargeImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetTutorialPageImage() {
        // when .. then
        DataRepository2.getInstance().getTutorials().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().tutorialImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetTutorialPageLargeImage() {
        // when .. then
        DataRepository2.getInstance().getTutorials().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().tutorialImageLargeProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetRealWorldImage() {
        // when .. then
        DataRepository2.getInstance().getRealWorldApps().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().realWorldAppImageProperty(item);
            assertNotNull(property, "small image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "small image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetRealWorldImageLarge() {
        // when .. then
        DataRepository2.getInstance().getRealWorldApps().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().realWorldAppLargeImageProperty(item);
            assertNotNull(property, "large image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "large image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetRealWorldBanner() {
        // when .. then
        DataRepository2.getInstance().getRealWorldApps().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().realWorldAppBannerImageProperty(item);
            assertNotNull(property, "banner image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "banner image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetToolImage() {
        // when .. then
        DataRepository2.getInstance().getTools().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().toolImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetUtilityImage() {
        // when .. then
        DataRepository2.getInstance().getUtilities().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().utilityImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetCompanyImage() {
        // when .. then
        DataRepository2.getInstance().getCompanies().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().companyImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetBookCoverImage() {
        // when .. then
        DataRepository2.getInstance().getBooks().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().bookCoverImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Disabled
    public void shouldGetYouTubeImage() {
        // when .. then
        DataRepository2.getInstance().getVideos().forEach(item -> {
            System.out.println("Youtube image url: " + ImageManager.getInstance().youTubeImageURL(item));
            ObjectProperty<Image> property = ImageManager.getInstance().youTubeImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId() + ", name = " + item.getName());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId() + ", name = " + item.getName());
        });
    }

    @Disabled
    public void shouldGetGithubAvatarImage() {
        // given
        User user = new User();
        user.setLogin("dlemmermann");

        PullRequest pr = new PullRequest();
        pr.setNumber(999);
        pr.setUser(user);

        // when
        ObjectProperty<Image> property = ImageManager.getInstance().githubAvatarImageProperty(pr.getUser().getLogin());

        // then
        assertNotNull(property, "image property is null for avatar");
        assertNotNull(property.get(), "image is missing for avatar");
    }

    @Test
    public void shouldGetDocumentationImage() {
        // when .. then
        DataRepository2.getInstance().getDocumentation().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().documentationImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetLibraryFeaturedImage() {
        // when .. then
        DataRepository2.getInstance().getLibraries().forEach(item -> {
            ObjectProperty<Image> featuredImageProperty = ImageManager.getInstance().libraryFeaturedImageProperty(item);
            assertNotNull(featuredImageProperty, "image featuredImageProperty is null for item ID " + item.getId());
            // featuredImageProperty.get() Can be null.
        });

    }
}
