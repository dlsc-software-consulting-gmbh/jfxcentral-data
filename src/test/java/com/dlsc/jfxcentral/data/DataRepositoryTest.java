package com.dlsc.jfxcentral.data;

import com.dlsc.jfxcentral.data.model.*;
import com.dlsc.jfxcentral.data.pull.PullRequest;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
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
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class DataRepositoryTest {

    @BeforeAll
    public static void setup() {
        DataRepository.ASYNC = false;
        DataRepository.BASE_URL = "file://" + System.getProperty("user.dir") + "/";
    }

    @Test
    public void shouldLoadData() {

        // when
        DataRepository repository = DataRepository.getInstance();

        // then
        assertTrue(!repository.getBlogs().isEmpty());
        assertTrue(!repository.getBooks().isEmpty());
        assertTrue(!repository.getCompanies().isEmpty());
        assertTrue(!repository.getDownloads().isEmpty());
        assertTrue(!repository.getLibraries().isEmpty());
        assertTrue(!repository.getNews().isEmpty());
        assertTrue(!repository.getPeople().isEmpty());
        assertTrue(!repository.getRealWorldApps().isEmpty());
        assertTrue(!repository.getTools().isEmpty());
        assertTrue(!repository.getVideos().isEmpty());
        assertTrue(!repository.getTutorials().isEmpty());

        assertTrue(StringUtils.isNotBlank(repository.getHomeText()));
        assertTrue(StringUtils.isNotBlank(repository.getOpenJFXText()));
    }

    @Test
    public void shouldClearData() {
        // given
        DataRepository repository = DataRepository.getInstance();

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
        assertTrue(repository.getVideos().isEmpty());
        assertTrue(repository.getTutorials().isEmpty());

        assertTrue(StringUtils.isBlank(repository.getHomeText()));
        assertTrue(StringUtils.isBlank(repository.getOpenJFXText()));

        // make sure to load the data again, otherwise the following tests will have nothing to work with
        repository.loadData();
    }

    @Test
    public void shouldRefreshData() {
        // given
        DataRepository repository = DataRepository.getInstance();

        // when
        repository.loadData();

        // then
        assertTrue(!repository.getBlogs().isEmpty());
        assertTrue(!repository.getBooks().isEmpty());
        assertTrue(!repository.getCompanies().isEmpty());
        assertTrue(!repository.getDownloads().isEmpty());
        assertTrue(!repository.getLibraries().isEmpty());
        assertTrue(!repository.getNews().isEmpty());
        assertTrue(!repository.getPeople().isEmpty());
        assertTrue(!repository.getRealWorldApps().isEmpty());
        assertTrue(!repository.getTools().isEmpty());
        assertTrue(!repository.getVideos().isEmpty());
        assertTrue(!repository.getTutorials().isEmpty());

        assertTrue(StringUtils.isNotBlank(repository.getHomeText()));
        assertTrue(StringUtils.isNotBlank(repository.getOpenJFXText()));
    }

    @Test
    public void shouldLoadLibraryDescription() {
        // given
        DataRepository repository = DataRepository.getInstance();

        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(lib -> {
            StringProperty text = repository.libraryReadMeProperty(lib);

            // then
            assertNotNull(text.get(), "text missing for library ID " + lib.getId());
        });
    }

    @Test
    public void shouldLoadLibraryInfo() {
        // given
        DataRepository repository = DataRepository.getInstance();

        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(lib -> {
            ObjectProperty<LibraryInfo> info = repository.libraryInfoProperty(lib);

            // then
            assertNotNull(info.get(), "info missing for library ID " + lib.getId());
        });
    }


    @Test
    public void shouldLoadLibraryInfoFiles() {
        // given
        DataRepository repository = DataRepository.getInstance();

        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(lib -> {
            ObjectProperty<LibraryInfo> info = repository.libraryInfoProperty(lib);


            LibraryInfo libraryInfo = info.get();
            libraryInfo.getImages().forEach(image -> {
                String path = image.getPath();

                // then
                assertTrue(StringUtils.isNotBlank(path));

                try {
                    URL url = new URL(DataRepository.BASE_URL + "libraries/" + lib.getId() + "/" + path);
                    System.out.println("library info file url = " + url.toExternalForm());
                    File file = new File(url.toURI());
                    assertTrue(file.exists());
                } catch (MalformedURLException | URISyntaxException e) {
                    fail(e);
                }
            });
        });
    }

    @Test
    public void shouldLoadPersonDescription() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            StringProperty text = repository.personDescriptionProperty(person);

            // then
            assertTrue(StringUtils.isNotBlank(text.get()), "text missing for person ID " + person.getId());
        });
    }

    @Test
    public void shouldLoadNewsText() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getNews().isEmpty());

        // when
        repository.getNews().forEach(news -> {
            StringProperty text = repository.newsTextProperty(news);

            // then
            assertTrue(StringUtils.isNotBlank(text.get()), "text missing for news ID " + news.getId());
        });
    }

    @Test
    public void shouldLoadTutorialText() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getTutorials().isEmpty());

        // when
        repository.getTutorials().forEach(tutorial -> {
            StringProperty text = repository.tutorialTextProperty(tutorial);

            // then
            assertTrue(StringUtils.isNotBlank(text.get()), "text missing for tutorial ID " + tutorial.getId());
        });
    }

    @Test
    public void shouldLoadRealWorldDescription() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getRealWorldApps().isEmpty());

        // when
        repository.getRealWorldApps().forEach(app -> {
            StringProperty text = repository.realWorldAppDescriptionProperty(app);

            // then
            assertTrue(StringUtils.isNotBlank(text.get()), "text missing for real world app ID " + app.getId());
        });
    }

    @Test
    public void shouldLoadCompanyDescription() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getCompanies().isEmpty());

        // when
        repository.getCompanies().forEach(company -> {
            StringProperty text = repository.companyDescriptionProperty(company);

            // then
            assertTrue(StringUtils.isNotBlank(text.get()), "text missing for company ID " + company.getId());
        });
    }

    @Test
    public void shouldLoadDownloadsDescription() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getDownloads().isEmpty());

        // when
        repository.getDownloads().forEach(download -> {
            StringProperty text = repository.downloadTextProperty(download);

            // then
            assertTrue(StringUtils.isNotBlank(text.get()), "text missing for download ID " + download.getId());
        });
    }

    @Test
    public void shouldLoadBooksDescription() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getBooks().isEmpty());

        // when
        repository.getBooks().forEach(book -> {
            StringProperty text = repository.bookTextProperty(book);

            // then
            assertTrue(StringUtils.isNotBlank(text.get()), "text missing for book ID " + book.getId());
        });
    }

    @Test
    public void shouldLoadToolsDescription() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getTools().isEmpty());

        // when
        repository.getTools().forEach(tool -> {
            StringProperty text = repository.toolDescriptionProperty(tool);

            // then
            assertTrue(StringUtils.isNotBlank(text.get()), "text missing for tool ID " + tool.getId());
        });
    }

    @Test
    public void shouldLoadLibraryArtifactVersion() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(lib -> {

            String groupId = lib.getGroupId();
            String artifactId = lib.getArtifactId();

            if (StringUtils.isNotBlank(groupId) && StringUtils.isNotBlank(artifactId)) {
                StringProperty version = repository.getArtifactVersion(lib);

                System.out.println(lib.getId() + " -> " + version.get());

                // then
                assertTrue(StringUtils.isNotBlank(version.get()), "unable to retrieve artifact version for library ID " + lib.getId());
            }
        });
    }

    @Test
    public void shouldGetVideosByPerson() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            ListProperty<Video> list = repository.getVideosByModelObject(person);

            // then
            assertNotNull(list.get(), "missing video list for person " + person.getId());

            if (person.getId().equals("d.lemmermann")) {
                assertTrue(!list.get().isEmpty(), "no videos returned for person d.lemmermann");
            }

            if (person.getId().equals("h.ebbers")) {
                assertTrue(!list.get().isEmpty(), "no videos returned for person h.ebbers");
            }
        });
    }

    @Test
    public void shouldGetBlogsByPerson() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            ListProperty<Blog> list = repository.getBlogsByModelObject(person);

            // then
            assertNotNull(list.get(), "missing blog list for person " + person.getId());

            if (person.getId().equals("d.lemmermann")) {
                assertTrue(!list.get().isEmpty(), "no blogs returned for person d.lemmermann");
            }
        });
    }

    @Test
    public void shouldNotGetGuiGarageBlog() {
        // given
        DataRepository repository = DataRepository.getInstance();

        // when
        Optional<Blog> guigarage = repository.getBlogById("guigarage");

        // then
        assertTrue(guigarage.isEmpty());
    }

    @Test
    public void shouldGetLibrariesByPerson() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            ListProperty<Library> list = repository.getLibrariesByModelObject(person);

            // then
            assertNotNull(list.get(), "missing library list for person " + person.getId());

            if (person.getId().equals("d.lemmermann")) {
                assertTrue(!list.get().isEmpty(), "no libraries returned for person d.lemmermann");
            }
        });
    }

    @Test
    public void shouldGetBooksByPerson() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            ListProperty<Book> list = repository.getBooksByModelObject(person);

            // then
            assertNotNull(list.get(), "missing books list for person " + person.getId());

            if (person.getId().equals("h.ebbers")) {
                assertTrue(!list.get().isEmpty(), "no libraries returned for person h.ebbers");
            }
        });
    }

    @Test
    public void shouldGetTutorialsByPerson() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            ListProperty<Tutorial> list = repository.getTutorialsByModelObject(person);

            // then
            assertNotNull(list.get(), "missing tutorials list for person " + person.getId());

            if (person.getId().equals("j.jenkov")) {
                assertTrue(!list.get().isEmpty(), "no tutorials returned for person j.jenkov");
            }
        });
    }

    @Test
    public void shouldGetDownloadsByPerson() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            ListProperty<Download> list = repository.getDownloadsByModelObject(person);

            // then
            assertNotNull(list.get(), "missing downloads list for person " + person.getId());

            if (person.getId().equals("d.lemmermann")) {
                assertTrue(!list.get().isEmpty(), "no tutorials returned for person d.lemmermann");
            }
        });
    }

    @Test
    public void shouldGetPersonById() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            Optional<Person> result = repository.getPersonById(person.getId());

            // then
            assertNotNull(result.get(), "no person returned for ID " + person.getId());
        });
    }

    @Test
    public void shouldGetCompanyById() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getCompanies().isEmpty());

        // when
        repository.getCompanies().forEach(company -> {
            Optional<Company> result = repository.getCompanyById(company.getId());

            // then
            assertNotNull(result.get(), "no company returned for ID " + company.getId());
        });
    }

    @Test
    public void shouldGetLibraryById() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(library -> {
            Optional<Library> result = repository.getLibraryById(library.getId());

            // then
            assertNotNull(result.get(), "no library returned for ID " + library.getId());
        });
    }

    @Test
    public void shouldGetBlogById() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getBlogs().isEmpty());

        // when
        repository.getBlogs().forEach(blog -> {
            Optional<Blog> result = repository.getBlogById(blog.getId());

            // then
            assertNotNull(result.get(), "no blog returned for ID " + blog.getId());
        });
    }

    @Test
    public void shouldGetBookById() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getBooks().isEmpty());

        // when
        repository.getBooks().forEach(book -> {
            Optional<Book> result = repository.getBookById(book.getId());

            // then
            assertNotNull(result.get(), "no book returned for ID " + book.getId());
        });
    }

    @Test
    public void shouldGetRealWorldAppById() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getRealWorldApps().isEmpty());

        // when
        repository.getRealWorldApps().forEach(real -> {
            Optional<RealWorldApp> result = repository.getRealWorldAppById(real.getId());

            // then
            assertNotNull(result.get(), "no real world app returned for ID " + real.getId());
        });
    }

    @Test
    public void shouldGetToolById() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getTools().isEmpty());

        // when
        repository.getTools().forEach(tool -> {
            Optional<Tool> result = repository.getToolById(tool.getId());

            // then
            assertNotNull(result.get(), "no tool returned for ID " + tool.getId());
        });
    }

    @Test
    public void shouldGetDownloadById() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getDownloads().isEmpty());

        // when
        repository.getDownloads().forEach(download -> {
            Optional<Download> result = repository.getDownloadById(download.getId());

            // then
            assertNotNull(result.get(), "no download returned for ID " + download.getId());
        });
    }

    @Test
    public void shouldHaveValidDownloadsURL() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getDownloads().isEmpty());

        // when
        repository.getDownloads().forEach(download -> {
            download.getFiles().forEach(file -> {
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

                        assertEquals(HttpURLConnection.HTTP_OK, responseCode);
                    } catch (MalformedURLException ex) {
                        fail("url was invalid, url = " + externalizedUrl);
                    } catch (IOException e) {
                        fail(e);
                    }
                }
            });
        });
    }

    @Test
    public void shouldGetOpenJFXPullRequests() {
        // given
        DataRepository repository = DataRepository.getInstance();

        // when
        List<PullRequest> pullRequests = repository.loadPullRequests();

        // then
        assertFalse(pullRequests.isEmpty());
    }

    @Test
    public void shouldGetTutorialsByLibrary() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(library -> {
            ListProperty<Tutorial> list = repository.getTutorialsByModelObject(library);

            // then
            assertNotNull(list.get(), "missing tutorials list for library " + library.getId());

            if (library.getId().equals("fxgl")) {
                assertTrue(!list.get().isEmpty(), "no tutorials returned for library fxgl");
            }
        });
    }

    @Test
    public void shouldGetVideosByLibrary() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(library -> {
            ListProperty<Video> list = repository.getVideosByModelObject(library);

            // then
            assertNotNull(list.get(), "missing video list for library " + library.getId());

            if (library.getId().equals("tilesfx")) {
                assertTrue(!list.get().isEmpty(), "no videos returned for library tilesfx");
            }
        });
    }

    @Test
    public void shouldGetDownloadsByLibrary() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(library -> {
            ListProperty<Download> list = repository.getDownloadsByModelObject(library);

            // then
            assertNotNull(list.get(), "missing downloads list for library " + library.getId());

            if (library.getId().equals("tilesfx")) {
                assertTrue(!list.get().isEmpty(), "no downloads returned for library tilesfx");
            }
        });
    }
}
