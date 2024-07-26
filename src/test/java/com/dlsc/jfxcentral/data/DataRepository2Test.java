package com.dlsc.jfxcentral.data;

import com.dlsc.jfxcentral.data.model.Blog;
import com.dlsc.jfxcentral.data.model.Book;
import com.dlsc.jfxcentral.data.model.Company;
import com.dlsc.jfxcentral.data.model.Documentation;
import com.dlsc.jfxcentral.data.model.Download;
import com.dlsc.jfxcentral.data.model.IkonliPack;
import com.dlsc.jfxcentral.data.model.LearnJavaFX;
import com.dlsc.jfxcentral.data.model.LearnMobile;
import com.dlsc.jfxcentral.data.model.LearnRaspberryPi;
import com.dlsc.jfxcentral.data.model.Library;
import com.dlsc.jfxcentral.data.model.LibraryInfo;
import com.dlsc.jfxcentral.data.model.Person;
import com.dlsc.jfxcentral.data.model.Post;
import com.dlsc.jfxcentral.data.model.RealWorldApp;
import com.dlsc.jfxcentral.data.model.Tip;
import com.dlsc.jfxcentral.data.model.Tool;
import com.dlsc.jfxcentral.data.model.Tutorial;
import com.dlsc.jfxcentral.data.model.Utility;
import com.dlsc.jfxcentral.data.model.Video;
import javafx.beans.property.StringProperty;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class DataRepository2Test {

    @BeforeAll
    public static void setup() {
        DataRepository2.setTesting(true);
    }

    @Test
    public void shouldLoadData() {

        // when
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        // then
        assertFalse(repository.getBlogs().isEmpty(), "Blogs");
        assertFalse(repository.getBooks().isEmpty(), "Books");
        assertFalse(repository.getCompanies().isEmpty(), "Companies");
        assertFalse(repository.getDownloads().isEmpty(), "Downloads");
        assertFalse(repository.getLibraries().isEmpty(), "Libraries");
        assertFalse(repository.getNews().isEmpty(), "News");
        assertFalse(repository.getPeople().isEmpty(), "People");
        assertFalse(repository.getRealWorldApps().isEmpty(), "Real world apps");
        assertFalse(repository.getTools().isEmpty(), "Tools");
        assertFalse(repository.getUtilities().isEmpty(), "Utilities");
        assertFalse(repository.getVideos().isEmpty(), "Videos");
        assertFalse(repository.getTutorials().isEmpty(), "Tutorials");
        assertFalse(repository.getIkonliPacks().isEmpty(), "Ikonli packs");
        assertFalse(repository.getMembers().isEmpty(), "Members");
        assertFalse(repository.getDocumentation().isEmpty(), "Documentation");
        assertFalse(repository.getLearnJavaFX().isEmpty(), "LearnJavaFX");
        assertFalse(repository.getLearnMobile().isEmpty(), "LearnMobile");
        assertFalse(repository.getLearnRaspberryPi().isEmpty(), "LearnRaspberryPi");

        assertTrue(StringUtils.isNotBlank(repository.getHomeText()));
        assertTrue(StringUtils.isNotBlank(repository.getOpenJFXText()));
    }

    @Test
    public void shouldClearData() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        // when
        repository.clearData();

        // then
        assertTrue(repository.getBlogs().isEmpty());
        assertTrue(repository.getBooks().isEmpty());
        assertTrue(repository.getCompanies().isEmpty());
        assertTrue(repository.getDownloads().isEmpty());
        assertTrue(repository.getLibraries().isEmpty());
        assertTrue(repository.getNews().isEmpty());
        assertTrue(repository.getPeople().isEmpty());
        assertTrue(repository.getRealWorldApps().isEmpty());
        assertTrue(repository.getTools().isEmpty());
        assertTrue(repository.getUtilities().isEmpty());
        assertTrue(repository.getVideos().isEmpty());
        assertTrue(repository.getTutorials().isEmpty());
        assertTrue(repository.getIkonliPacks().isEmpty());
        assertTrue(repository.getMembers().isEmpty());
        assertTrue(repository.getDocumentation().isEmpty());
        assertTrue(repository.getLearnJavaFX().isEmpty());
        assertTrue(repository.getLearnMobile().isEmpty());
        assertTrue(repository.getLearnRaspberryPi().isEmpty());

        assertTrue(StringUtils.isBlank(repository.getHomeText()));
        assertTrue(StringUtils.isBlank(repository.getOpenJFXText()));

        // make sure to load the data again, otherwise the following tests will have nothing to work with
        repository.reload();
    }

    @Test
    public void shouldRefreshData() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        // when
        repository.reload();

        // then
        assertFalse(repository.getBlogs().isEmpty());
        assertFalse(repository.getBooks().isEmpty());
        assertFalse(repository.getCompanies().isEmpty());
        assertFalse(repository.getDownloads().isEmpty());
        assertFalse(repository.getLibraries().isEmpty());
        assertFalse(repository.getNews().isEmpty());
        assertFalse(repository.getPeople().isEmpty());
        assertFalse(repository.getRealWorldApps().isEmpty());
        assertFalse(repository.getTools().isEmpty());
        assertFalse(repository.getUtilities().isEmpty());
        assertFalse(repository.getVideos().isEmpty());
        assertFalse(repository.getTutorials().isEmpty());
        assertFalse(repository.getIkonliPacks().isEmpty());
        assertFalse(repository.getMembers().isEmpty());
        assertFalse(repository.getDocumentation().isEmpty());
        assertFalse(repository.getLearnJavaFX().isEmpty());
        assertFalse(repository.getLearnMobile().isEmpty());
        assertFalse(repository.getLearnRaspberryPi().isEmpty());

        assertTrue(StringUtils.isNotBlank(repository.getHomeText()));
        assertTrue(StringUtils.isNotBlank(repository.getOpenJFXText()));
    }

    @Test
    public void shouldLoadLibraryDescription() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(lib -> {
            String text = repository.getLibraryReadMe(lib);

            // then
            assertNotNull(text, "text missing for library ID " + lib.getId());
        });
    }

    @Test
    public void shouldLoadLibraryInfo() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(lib -> {
            LibraryInfo info = repository.getLibraryInfo(lib);

            // then
            assertNotNull(info, "info missing for library ID " + lib.getId());
        });
    }

    @Test
    public void shouldLoadLibraryInfoFiles() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(lib -> {
            LibraryInfo info = repository.getLibraryInfo(lib);

            info.getImages().forEach(image -> {
                assertNotNull(image, "image null in library info of library " + lib.getName());

                String path = image.getPath();

                // then
                assertTrue(StringUtils.isNotBlank(path));

                File file = new File(DataRepository2.getInstance().getRepositoryDirectory(), "libraries/" + lib.getId() + "/" + path);
                assertTrue(file.exists(), "file does not exist: " + file.getAbsolutePath() + " for library " + lib.getName());

            });
        });
    }

    @Test
    public void shouldLoadPersonDescription() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            String text = repository.getPersonReadMe(person);

            // then
            assertTrue(StringUtils.isNotBlank(text), "text missing for person ID " + person.getId());
        });
    }

    @Test
    public void shouldLoadPersonMastodon() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getPeople().isEmpty());

        // when
        Optional<Person> fd = repository.getPeople().stream()
                .filter(p -> p.getId().equals("f.delporte"))
                .findFirst();

        assertTrue(fd.isPresent());
        assertEquals("https://foojay.social/@frankdelporte", fd.get().getMastodon(), "mastodon link not loaded");
    }

    @Test
    public void shouldLoadMemberDescription() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getMembers().isEmpty());

        // when
        repository.getMembers().forEach(member -> {
            String text = repository.getMemberReadMe(member);
            System.out.println("text = " + text);
            // then
            assertTrue(StringUtils.isNotBlank(text), "text missing for person ID " + member.getId());
        });
    }

    @Test
    public void shouldLoadLinksOfTheWeek() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getLinksOfTheWeek().isEmpty());

        // when
        repository.getLinksOfTheWeek().forEach(links -> {
            String text = repository.getLinksOfTheWeekReadMe(links);

            // then
            assertTrue(StringUtils.isNotBlank(text), "text missing for links of the week ID " + links.getId());
        });
    }

    @Test
    public void shouldLoadNewsText() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getNews().isEmpty());

        // when
        repository.getNews().forEach(news -> {
            String text = repository.getNewsText(news);

            // then
            assertTrue(StringUtils.isNotBlank(text), "text missing for news ID " + news.getId());
        });
    }

    @Test
    public void shouldLoadTutorialText() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getTutorials().isEmpty());

        // when
        repository.getTutorials().forEach(tutorial -> {
            String text = repository.getTutorialReadMe(tutorial);

            // then
            assertTrue(StringUtils.isNotBlank(text), "text missing for tutorial ID " + tutorial.getId());
        });
    }

    @Test
    public void shouldLoadRealWorldDescription() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getRealWorldApps().isEmpty());

        // when
        repository.getRealWorldApps().forEach(app -> {
            String text = repository.getRealWorldReadMe(app);

            // then
            assertTrue(StringUtils.isNotBlank(text), "text missing for real world app ID " + app.getId());
        });
    }

    @Test
    public void shouldLoadCompanyDescription() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getCompanies().isEmpty());

        // when
        repository.getCompanies().forEach(company -> {
            String text = repository.getCompanyReadMe(company);

            // then
            assertTrue(StringUtils.isNotBlank(text), "text missing for company ID " + company.getId());
        });
    }

    @Test
    public void shouldLoadDownloadsDescription() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getDownloads().isEmpty());

        // when
        repository.getDownloads().forEach(download -> {
            String text = repository.getDownloadReadMe(download);

            // then
            assertTrue(StringUtils.isNotBlank(text), "text missing for download ID " + download.getId());
        });
    }

    @Test
    public void shouldLoadBooksDescription() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getBooks().isEmpty());

        // when
        repository.getBooks().forEach(book -> {
            String text = repository.getBookReadMe(book);

            // then
            assertTrue(StringUtils.isNotBlank(text), "text missing for book ID " + book.getId());
        });
    }

    @Test
    public void shouldLoadBlogs() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getBlogs().isEmpty());

        // when
        repository.getBlogs().forEach(blog -> {
            System.out.println("--------------------- blog: " + blog.getName());
            List<Post> posts = repository.loadPosts(blog);
            posts.forEach(post -> System.out.println("post title: " + post.getSyndEntry().getTitle()));
        });
    }

    @Test
    public void shouldLoadToolsDescription() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getTools().isEmpty());

        // when
        repository.getTools().forEach(tool -> {
            String text = repository.getToolReadMe(tool);

            // then
            assertTrue(StringUtils.isNotBlank(text), "text missing for tool ID " + tool.getId());
        });
    }

    @Test
    public void shouldLoadUtilityDescription() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getUtilities().isEmpty());

        // when
        repository.getUtilities().forEach(utility -> {
            String text = repository.getUtilityReadMe(utility);

            // then
            assertTrue(StringUtils.isNotBlank(text), "text missing for utility ID " + utility.getId());
        });
    }

    @Test
    public void shouldLoadLibraryArtifactVersion() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(lib -> {

            String groupId = lib.getGroupId();
            String artifactId = lib.getArtifactId();

            System.out.println("  >>>>>>> groupId = " + groupId + " artifactId = " + artifactId);

            if (StringUtils.isNotBlank(groupId) && StringUtils.isNotBlank(artifactId)) {
                StringProperty version = repository.getArtifactVersion(lib);

                System.out.println(lib.getId() + " -> " + version.get());

                // then
                System.out.println(">>>>> " + lib.getId());
                assertTrue(StringUtils.isNotBlank(version.get()), "unable to retrieve artifact version for library ID " + lib.getId());
            }
        });
    }

    @Test
    public void shouldGetVideosByPerson() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            List<Video> list = repository.getVideosByModelObject(person);

            // then
            assertNotNull(list, "missing video list for person " + person.getId());

            if (person.getId().equals("d.lemmermann")) {
                assertFalse(list.isEmpty(), "no videos returned for person d.lemmermann");
            }

            if (person.getId().equals("h.ebbers")) {
                assertFalse(list.isEmpty(), "no videos returned for person h.ebbers");
            }
        });
    }

    @Test
    public void shouldGetBlogsByPerson() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            List<Blog> list = repository.getBlogsByModelObject(person);

            // then
            assertNotNull(list, "missing blog list for person " + person.getId());

            if (person.getId().equals("d.lemmermann")) {
                assertFalse(list.isEmpty(), "no blogs returned for person d.lemmermann");
            }
        });
    }

    @Test
    public void shouldNotGetGuiGarageBlog() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        // when
        Optional<Blog> guigarage = repository.getBlogById("guigarage");

        // then
        assertTrue(guigarage.isEmpty());
    }

    @Test
    public void shouldGetLibrariesByPerson() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            List<Library> list = repository.getLibrariesByModelObject(person);

            // then
            assertNotNull(list, "missing library list for person " + person.getId());

            if (person.getId().equals("d.lemmermann")) {
                assertFalse(list.isEmpty(), "no libraries returned for person d.lemmermann");
            }
        });
    }

    @Test
    public void shouldGetTipsByPerson() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            List<Tip> list = repository.getTipsByModelObject(person);

            // then
            assertNotNull(list, "missing tip list for person " + person.getId());

            if (person.getId().equals("d.lemmermann")) {
                assertFalse(list.isEmpty(), "no libraries returned for person d.lemmermann");
                assertTrue(list.size() > 1, "not enough tips returned for d.lemmermann");
            }
        });
    }

    @Test
    public void shouldGetBooksByPerson() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            List<Book> list = repository.getBooksByModelObject(person);

            // then
            assertNotNull(list, "missing books list for person " + person.getId());

            if (person.getId().equals("h.ebbers")) {
                assertFalse(list.isEmpty(), "no libraries returned for person h.ebbers");
            }
        });
    }

    @Test
    public void shouldGetTutorialsByPerson() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            List<Tutorial> list = repository.getTutorialsByModelObject(person);

            // then
            assertNotNull(list, "missing tutorials list for person " + person.getId());

            if (person.getId().equals("j.jenkov")) {
                assertFalse(list.isEmpty(), "no tutorials returned for person j.jenkov");
            }
        });
    }

    @Test
    public void shouldGetDownloadsByPerson() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            List<Download> list = repository.getDownloadsByModelObject(person);

            // then
            assertNotNull(list, "missing downloads list for person " + person.getId());

            if (person.getId().equals("d.lemmermann")) {
                assertFalse(list.isEmpty(), "no tutorials returned for person d.lemmermann");
            }
        });
    }

    @Test
    public void shouldGetPersonById() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            Optional<Person> result = repository.getPersonById(person.getId());

            // then
            assertTrue(result.isPresent(), "no person returned for ID " + person.getId());
        });
    }

    @Test
    public void shouldGetCompanyById() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getCompanies().isEmpty());

        // when
        repository.getCompanies().forEach(company -> {
            Optional<Company> result = repository.getCompanyById(company.getId());

            // then
            assertTrue(result.isPresent(), "no company returned for ID " + company.getId());
        });
    }

    @Test
    public void shouldGetLibraryById() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(library -> {
            Optional<Library> result = repository.getLibraryById(library.getId());

            // then
            assertTrue(result.isPresent(), "no library returned for ID " + library.getId());
        });
    }

    @Test
    public void shouldGetBlogById() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getBlogs().isEmpty());

        // when
        repository.getBlogs().forEach(blog -> {
            Optional<Blog> result = repository.getBlogById(blog.getId());

            // then
            assertTrue(result.isPresent(), "no blog returned for ID " + blog.getId());
        });
    }

    @Test
    public void shouldGetBookById() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getBooks().isEmpty());

        // when
        repository.getBooks().forEach(book -> {
            Optional<Book> result = repository.getBookById(book.getId());

            // then
            assertTrue(result.isPresent(), "no book returned for ID " + book.getId());
        });
    }

    @Test
    public void shouldGetLearnJavaFXById() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getLearnJavaFX().isEmpty());

        // when
        repository.getLearnJavaFX().forEach(learnJavaFX -> {
            Optional<LearnJavaFX> result = repository.getLearnJavaFXById(learnJavaFX.getId());

            // then
            assertTrue(result.isPresent(), "no LearnJavaFX returned for ID " + learnJavaFX.getId());

            LearnJavaFX findResult = repository.getByID(LearnJavaFX.class, learnJavaFX.getId());
            assertNotNull(findResult);

            boolean validIdResult = repository.isValidId(LearnJavaFX.class, learnJavaFX.getId());
            assertTrue(validIdResult);
        });
    }

    @Test
    public void shouldLoadLearnJavaFXDescription() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getLearnJavaFX().isEmpty());

        // when
        repository.getLearnJavaFX().forEach(learnJavaFX -> {
            String text = repository.getLearnJavaFXReadMe(learnJavaFX);

            // then
            assertNotNull(text, "text missing for learnJavaFX ID " + learnJavaFX.getId());
        });
    }

    @Test
    public void shouldGetLearnMobileById() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getLearnMobile().isEmpty());

        // when
        repository.getLearnMobile().forEach(learnMobile -> {
            Optional<LearnMobile> result = repository.getLearnMobileById(learnMobile.getId());

            // then
            assertTrue(result.isPresent(), "no LearnMobile returned for ID " + learnMobile.getId());

            LearnMobile findResult = repository.getByID(LearnMobile.class, learnMobile.getId());
            assertNotNull(findResult);

            boolean validIdResult = repository.isValidId(LearnMobile.class, learnMobile.getId());
            assertTrue(validIdResult);
        });
    }

    @Test
    public void shouldLoadLearnMobileDescription() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getLearnMobile().isEmpty());

        // when
        repository.getLearnMobile().forEach(learnMobile -> {
            String text = repository.getLearnMobileReadMe(learnMobile);

            // then
            assertNotNull(text, "text missing for learnMobile ID " + learnMobile.getId());
        });
    }

    @Test
    public void shouldGetLearnRaspberryPiById() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getLearnRaspberryPi().isEmpty());

        // when
        repository.getLearnRaspberryPi().forEach(learnRaspberryPi -> {
            Optional<LearnRaspberryPi> result = repository.getLearnRaspberryPiById(learnRaspberryPi.getId());

            // then
            assertTrue(result.isPresent(), "no LearnRaspberryPi returned for ID " + learnRaspberryPi.getId());

            LearnRaspberryPi findResult = repository.getByID(LearnRaspberryPi.class, learnRaspberryPi.getId());
            assertNotNull(findResult);

            boolean validIdResult = repository.isValidId(LearnRaspberryPi.class, learnRaspberryPi.getId());
            assertTrue(validIdResult);
        });
    }

    @Test
    public void shouldLoadLearnRaspberryPiDescription() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getLearnRaspberryPi().isEmpty());

        // when
        repository.getLearnRaspberryPi().forEach(learnRaspberryPi -> {
            String text = repository.getLearnRaspberryPiReadMe(learnRaspberryPi);

            // then
            assertNotNull(text, "text missing for learnRaspberryPi ID " + learnRaspberryPi.getId());
        });
    }

    @Test
    public void shouldGetRealWorldAppById() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getRealWorldApps().isEmpty());

        // when
        repository.getRealWorldApps().forEach(real -> {
            Optional<RealWorldApp> result = repository.getRealWorldAppById(real.getId());

            // then
            assertTrue(result.isPresent(), "no real world app returned for ID " + real.getId());
        });
    }

    @Test
    public void shouldGetToolById() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getTools().isEmpty());

        // when
        repository.getTools().forEach(tool -> {
            Optional<Tool> result = repository.getToolById(tool.getId());

            // then
            assertTrue(result.isPresent(), "no tool returned for ID " + tool.getId());
        });
    }

    @Test
    public void shouldGetUtilityById() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getUtilities().isEmpty());

        // when
        repository.getUtilities().forEach(utility -> {
            Optional<Utility> result = repository.getUtilityById(utility.getId());
            System.out.println(utility.getName() +" isOnlineSupported => "+ utility.isOnlineSupported());

            // then
            assertTrue(result.isPresent(), "no utility returned for ID " + utility.getId());
        });
    }

    @Test
    public void shouldGetDownloadById() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getDownloads().isEmpty());

        // when
        repository.getDownloads().forEach(download -> {
            Optional<Download> result = repository.getDownloadById(download.getId());

            // then
            assertTrue(result.isPresent(), "no download returned for ID " + download.getId());
        });
    }

    @Test
    public void shouldHaveValidDownloadsURL() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getDownloads().isEmpty());

        // when
        repository.getDownloads().forEach(download -> download.getFiles().forEach(file -> {
            String externalizedUrl = file.getUrl();
            if (StringUtils.isNotBlank(externalizedUrl)) {
                System.out.println("checking url: " + externalizedUrl);
                try {
                    URL url = new URL(externalizedUrl);
                    HttpURLConnection huc = (HttpURLConnection) url.openConnection();
                    huc.setRequestMethod("HEAD");

                    // then
                    int responseCode = huc.getResponseCode();
                    System.out.println("response: " + responseCode);

                    assertEquals(HttpURLConnection.HTTP_OK, responseCode, "checked url: " + url.toExternalForm());
                } catch (MalformedURLException ex) {
                    fail("url was invalid, url = " + externalizedUrl);
                } catch (IOException e) {
                    fail(e);
                }
            }
        }));
    }

    @Test
    public void shouldGetTutorialsByLibrary() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(library -> {
            List<Tutorial> list = repository.getTutorialsByModelObject(library);

            // then
            assertNotNull(list, "missing tutorials list for library " + library.getId());

            if (library.getId().equals("fxgl")) {
                assertFalse(list.isEmpty(), "no tutorials returned for library fxgl");
            }
        });
    }

    @Test
    public void shouldGetVideosByLibrary() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(library -> {
            List<Video> list = repository.getVideosByModelObject(library);

            // then
            assertNotNull(list, "missing video list for library " + library.getId());

            if (library.getId().equals("tilesfx")) {
                assertFalse(list.isEmpty(), "no videos returned for library tilesfx");
            }
        });
    }

    @Test
    public void shouldGetDownloadsByLibrary() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(library -> {
            List<Download> list = repository.getDownloadsByModelObject(library);

            // then
            assertNotNull(list, "missing downloads list for library " + library.getId());

            if (library.getId().equals("tilesfx")) {
                assertFalse(list.isEmpty(), "no downloads returned for library tilesfx");
            }
        });
    }

    @Test
    public void shouldGetIkonliPacks() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        // when
        List<IkonliPack> ikonliPacks = repository.getIkonliPacks();

        // then
        assertFalse(ikonliPacks.isEmpty());
    }

    @Test
    public void shouldGetDocumentation() {
        // given
        DataRepository2 repository = DataRepository2.getInstance();
        repository.reload();

        // when
        List<Documentation> documentation = repository.getDocumentation();

        // then
        assertFalse(documentation.isEmpty());
    }
}
