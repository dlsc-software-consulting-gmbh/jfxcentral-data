package com.dlsc.jfxcentral.data;

import com.dlsc.jfxcentral.data.model.*;
import com.dlsc.jfxcentral.data.pull.PullRequest;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
        assertTrue(!repository.getPosts().isEmpty());
        assertTrue(!repository.getRealWorldApps().isEmpty());
        assertTrue(!repository.getTools().isEmpty());
        assertTrue(!repository.getVideos().isEmpty());

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
        assertTrue(repository.getPosts().isEmpty());
        assertTrue(repository.getRealWorldApps().isEmpty());
        assertTrue(repository.getTools().isEmpty());
        assertTrue(repository.getVideos().isEmpty());

        assertTrue(StringUtils.isBlank(repository.getHomeText()));
        assertTrue(StringUtils.isBlank(repository.getOpenJFXText()));

        // make sure to load the data again, otherwise the following tests will have nothing to work with
        repository.refreshData();
    }

    @Test
    public void shouldRefreshData() {
        // given
        DataRepository repository = DataRepository.getInstance();

        // when
        repository.refreshData();

        // then
        assertTrue(!repository.getBlogs().isEmpty());
        assertTrue(!repository.getBooks().isEmpty());
        assertTrue(!repository.getCompanies().isEmpty());
        assertTrue(!repository.getDownloads().isEmpty());
        assertTrue(!repository.getLibraries().isEmpty());
        assertTrue(!repository.getNews().isEmpty());
        assertTrue(!repository.getPeople().isEmpty());
        assertTrue(!repository.getPosts().isEmpty());
        assertTrue(!repository.getRealWorldApps().isEmpty());
        assertTrue(!repository.getTools().isEmpty());
        assertTrue(!repository.getVideos().isEmpty());

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
    public void shouldLoadNews() {
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
            ListProperty<Video> list = repository.getVideosByPerson(person);

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
            ListProperty<Blog> list = repository.getBlogsByPerson(person);

            // then
            assertNotNull(list.get(), "missing blog list for person " + person.getId());

            if (person.getId().equals("d.lemmermann")) {
                assertTrue(!list.get().isEmpty(), "no blogs returned for person d.lemmermann");
            }

            if (person.getId().equals("h.ebbers")) {
                assertTrue(!list.get().isEmpty(), "no blogs returned for person h.ebbers");
            }
        });
    }

    @Test
    public void shouldGetLibrariesByPerson() {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            ListProperty<Library> list = repository.getLibrariesByPerson(person);

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
            ListProperty<Book> list = repository.getBooksByPerson(person);

            // then
            assertNotNull(list.get(), "missing books list for person " + person.getId());

            if (person.getId().equals("h.ebbers")) {
                assertTrue(!list.get().isEmpty(), "no libraries returned for person h.ebbers");
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
        repository.loadPullRequests();

        // then
        ObservableList<PullRequest> pullRequests = repository.getPullRequests();

        assertFalse(pullRequests.isEmpty());
    }
}
