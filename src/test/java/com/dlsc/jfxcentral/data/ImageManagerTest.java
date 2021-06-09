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
 * Tests the existence of the MANDATOR images, e.g. news banner, library icon, etc...
 * Some model objects have OPTIONAL images, e.g. a "person".
 */
@ExtendWith(ApplicationExtension.class)
public class ImageManagerTest {

    @BeforeAll
    public static void setup() {
        DataRepository.ASYNC = false;
        DataRepository.BASE_URL = "file://" + System.getProperty("user.dir") + "/";
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
    public void shouldGetBlogPageImage() {
        // when .. then
        DataRepository.getInstance().getBlogs().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().blogPageImageProperty(item);
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
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
        });
    }

    @Test
    public void shouldGetRealWorldImageLarge() {
        // when .. then
        DataRepository.getInstance().getRealWorldApps().forEach(item -> {
            ObjectProperty<Image> property = ImageManager.getInstance().realWorldAppLargeImageProperty(item);
            assertNotNull(property, "image property is null for item ID " + item.getId());
            assertNotNull(property.get(), "image is missing for item ID " + item.getId());
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
