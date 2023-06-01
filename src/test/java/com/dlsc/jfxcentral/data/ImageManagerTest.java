package com.dlsc.jfxcentral.data;

import com.dlsc.jfxcentral.data.pull.PullRequest;
import com.dlsc.jfxcentral.data.pull.User;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests the existence of the MANDATORY images, e.g. news banner, library icon, etc...
 * Some model objects have OPTIONAL images, e.g. a "person".
 */
@ExtendWith(ApplicationExtension.class)
public class ImageManagerTest {

    @BeforeAll
    public static void setup() {
        DataRepository.setTesting(true);
    }

//    @Test
//    public void shouldThrowError() {
//        // when image doesn't exist
//        // then we should get an error
//        boolean exceptionThrown = false;
//        try {
//            ImageManager.getInstance().localImageProperty(new File(System.getProperty("user.home"), "/something/"), "banner.jpg", "not-existing");
//        } catch (Exception e) {
//            exceptionThrown = true;
//        }
//        assertTrue(exceptionThrown, "No exception was thrown!");
//    }

    @Test
    public void shouldGetPersonImage() {
        // when .. then
        DataRepository.getInstance().getPeople().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().personImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetNewsBanner() {
        // when .. then
        DataRepository.getInstance().getNews().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().newsBannerImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetDownloadBanner() {
        // when .. then
        DataRepository.getInstance().getDownloads().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().downloadBannerImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetTipBanner() {
        // when .. then
        DataRepository.getInstance().getTips().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().tipBannerImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetBlogPageImage() {
        // when .. then
        DataRepository.getInstance().getBlogs().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().blogPageImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetBlogIconImage() {
        // when .. then
        DataRepository.getInstance().getBlogs().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().blogIconImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetBlogPageLargeImage() {
        // when .. then
        DataRepository.getInstance().getBlogs().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().blogPageLargeImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetTutorialPageImage() {
        // when .. then
        DataRepository.getInstance().getTutorials().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().tutorialImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetTutorialPageLargeImage() {
        // when .. then
        DataRepository.getInstance().getTutorials().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().tutorialImageLargeProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetRealWorldImage() {
        // when .. then
        DataRepository.getInstance().getRealWorldApps().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().realWorldAppImageProperty(item);
            assertNotNull(property, "small image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "small image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetRealWorldImageLarge() {
        // when .. then
        DataRepository.getInstance().getRealWorldApps().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().realWorldAppLargeImageProperty(item);
            assertNotNull(property, "large image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "large image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetRealWorldBanner() {
        // when .. then
        DataRepository.getInstance().getRealWorldApps().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().realWorldAppBannerImageProperty(item);
            assertNotNull(property, "banner image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "banner image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetToolImage() {
        // when .. then
        DataRepository.getInstance().getTools().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().toolImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetCompanyImage() {
        // when .. then
        DataRepository.getInstance().getCompanies().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().companyImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetBookCoverImage() {
        // when .. then
        DataRepository.getInstance().getBooks().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().bookCoverImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetYouTubImage() {
        // when .. then
        DataRepository.getInstance().getVideos().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().youTubeImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
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
}
