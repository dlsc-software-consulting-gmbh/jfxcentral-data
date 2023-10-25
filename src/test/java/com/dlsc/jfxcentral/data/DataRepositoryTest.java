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
import com.dlsc.jfxcentral.data.model.Member;
import com.dlsc.jfxcentral.data.model.Person;
import com.dlsc.jfxcentral.data.model.Post;
import com.dlsc.jfxcentral.data.model.RealWorldApp;
import com.dlsc.jfxcentral.data.model.Tip;
import com.dlsc.jfxcentral.data.model.Tool;
import com.dlsc.jfxcentral.data.model.Tutorial;
import com.dlsc.jfxcentral.data.model.Utility;
import com.dlsc.jfxcentral.data.model.Video;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
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

@ExtendWith(ApplicationExtension.class)
public class DataRepositoryTest {

    @BeforeAll
    public static void setup() {
        DataRepository.setTesting(true);
    }

    @Test
    public void shouldLoadData() {

        // when
        DataRepository repository = DataRepository.getInstance();
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
        assertTrue(!repository.getUtilities().isEmpty());
        assertTrue(!repository.getVideos().isEmpty());
        assertTrue(!repository.getTutorials().isEmpty());
        assertTrue(!repository.getIkonliPacks().isEmpty());
        assertTrue(!repository.getMembers().isEmpty());
        assertTrue(!repository.getDocumentation().isEmpty());
        assertTrue(!repository.getLearnJavaFX().isEmpty());
        assertTrue(!repository.getLearnMobile().isEmpty());
        assertTrue(!repository.getLearnRaspberryPi().isEmpty());

        assertTrue(StringUtils.isNotBlank(repository.getHomeText()));
        assertTrue(StringUtils.isNotBlank(repository.getOpenJFXText()));
    }

    @Test
    public void shouldClearData() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

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
        repository.loadData();
    }

    @Test
    public void shouldRefreshData() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

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
        assertTrue(!repository.getUtilities().isEmpty());
        assertTrue(!repository.getVideos().isEmpty());
        assertTrue(!repository.getTutorials().isEmpty());
        assertTrue(!repository.getIkonliPacks().isEmpty());
        assertTrue(!repository.getMembers().isEmpty());
        assertTrue(!repository.getDocumentation().isEmpty());
        assertTrue(!repository.getLearnJavaFX().isEmpty());
        assertTrue(!repository.getLearnMobile().isEmpty());
        assertTrue(!repository.getLearnRaspberryPi().isEmpty());

        assertTrue(StringUtils.isNotBlank(repository.getHomeText()));
        assertTrue(StringUtils.isNotBlank(repository.getOpenJFXText()));
    }

    @Test
    public void shouldLoadLibraryDescription() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

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
        repository.loadData();

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
        repository.loadData();

        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(lib -> {
            ObjectProperty<LibraryInfo> info = repository.libraryInfoProperty(lib);

            LibraryInfo libraryInfo = info.get();
            libraryInfo.getImages().forEach(image -> {
                assertNotNull(image, "image null in library info of library " + lib.getName());

                String path = image.getPath();

                // then
                assertTrue(StringUtils.isNotBlank(path));

                File file = new File(DataRepository.getInstance().getRepositoryDirectory(), "libraries/" + lib.getId() + "/" + path);
                assertTrue(file.exists(), "file does not exist: " + file.getAbsolutePath() + " for library " + lib.getName());

            });
        });
    }

    @Test
    public void shouldLoadPersonDescription() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            StringProperty text = repository.personDescriptionProperty(person);

            // then
            assertTrue(StringUtils.isNotBlank(text.get()), "text missing for person ID " + person.getId());
        });
    }

    @Test
    public void shouldLoadMemberDescription() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getMembers().isEmpty());

        // when
        repository.getMembers().forEach(member -> {
            StringProperty text = repository.memberDescriptionProperty(member);

            // then
            assertTrue(StringUtils.isNotBlank(text.get()), "text missing for member ID " + member.getId());
        });
    }

    @Test
    public void shouldLoadLinksOfTheWeek() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getLinksOfTheWeek().isEmpty());

        // when
        repository.getLinksOfTheWeek().forEach(links -> {
            StringProperty text = repository.linksOfTheWeekTextProperty(links);

            // then
            assertTrue(StringUtils.isNotBlank(text.get()), "text missing for links of the week ID " + links.getId());
        });
    }

    @Test
    public void shouldLoadNewsText() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

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
        repository.loadData();

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
        repository.loadData();

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
        repository.loadData();

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
        repository.loadData();

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
        repository.loadData();

        assertFalse(repository.getBooks().isEmpty());

        // when
        repository.getBooks().forEach(book -> {
            StringProperty text = repository.bookTextProperty(book);

            // then
            assertTrue(StringUtils.isNotBlank(text.get()), "text missing for book ID " + book.getId());
        });
    }

    @Test
    public void shouldLoadBlogs() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

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
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getTools().isEmpty());

        // when
        repository.getTools().forEach(tool -> {
            StringProperty text = repository.toolDescriptionProperty(tool);

            // then
            assertTrue(StringUtils.isNotBlank(text.get()), "text missing for tool ID " + tool.getId());
        });
    }

    @Test
    public void shouldLoadUtilityDescription() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getUtilities().isEmpty());

        // when
        repository.getUtilities().forEach(utility -> {
            StringProperty text = repository.utilityDescriptionProperty(utility);

            // then
            assertTrue(StringUtils.isNotBlank(text.get()), "text missing for utility ID " + utility.getId());
        });
    }

    @Test
    public void shouldLoadLibraryArtifactVersion() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(lib -> {

            String groupId = lib.getGroupId();
            String artifactId = lib.getArtifactId();

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
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            List<Video> list = repository.getVideosByModelObject(person);

            // then
            assertNotNull(list, "missing video list for person " + person.getId());

            if (person.getId().equals("d.lemmermann")) {
                assertTrue(!list.isEmpty(), "no videos returned for person d.lemmermann");
            }

            if (person.getId().equals("h.ebbers")) {
                assertTrue(!list.isEmpty(), "no videos returned for person h.ebbers");
            }
        });
    }

    @Test
    public void shouldGetBlogsByPerson() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            List<Blog> list = repository.getBlogsByModelObject(person);

            // then
            assertNotNull(list, "missing blog list for person " + person.getId());

            if (person.getId().equals("d.lemmermann")) {
                assertTrue(!list.isEmpty(), "no blogs returned for person d.lemmermann");
            }
        });
    }

    @Test
    public void shouldNotGetGuiGarageBlog() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();


        // when
        Optional<Blog> guigarage = repository.getBlogById("guigarage");

        // then
        assertTrue(guigarage.isEmpty());
    }

    @Test
    public void shouldGetLibrariesByPerson() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            List<Library> list = repository.getLibrariesByModelObject(person);

            // then
            assertNotNull(list, "missing library list for person " + person.getId());

            if (person.getId().equals("d.lemmermann")) {
                assertTrue(!list.isEmpty(), "no libraries returned for person d.lemmermann");
            }
        });
    }

    @Test
    public void shouldGetTipsByPerson() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            List<Tip> list = repository.getTipsByModelObject(person);

            // then
            assertNotNull(list, "missing tip list for person " + person.getId());

            if (person.getId().equals("d.lemmermann")) {
                assertTrue(!list.isEmpty(), "no libraries returned for person d.lemmermann");
                assertTrue(list.size() > 1, "not enough tips returned for d.lemmermann");
            }
        });
    }

    @Test
    public void shouldGetBooksByPerson() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            List<Book> list = repository.getBooksByModelObject(person);

            // then
            assertNotNull(list, "missing books list for person " + person.getId());

            if (person.getId().equals("h.ebbers")) {
                assertTrue(!list.isEmpty(), "no libraries returned for person h.ebbers");
            }
        });
    }

    @Test
    public void shouldGetTutorialsByPerson() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            List<Tutorial> list = repository.getTutorialsByModelObject(person);

            // then
            assertNotNull(list, "missing tutorials list for person " + person.getId());

            if (person.getId().equals("j.jenkov")) {
                assertTrue(!list.isEmpty(), "no tutorials returned for person j.jenkov");
            }
        });
    }

    @Test
    public void shouldGetDownloadsByPerson() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getPeople().isEmpty());

        // when
        repository.getPeople().forEach(person -> {
            List<Download> list = repository.getDownloadsByModelObject(person);

            // then
            assertNotNull(list, "missing downloads list for person " + person.getId());

            if (person.getId().equals("d.lemmermann")) {
                assertTrue(!list.isEmpty(), "no tutorials returned for person d.lemmermann");
            }
        });
    }

    @Test
    public void shouldGetPersonById() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

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
        repository.loadData();

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
        repository.loadData();

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
        repository.loadData();

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
        repository.loadData();

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
        repository.loadData();

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
        repository.loadData();

        assertFalse(repository.getTools().isEmpty());

        // when
        repository.getTools().forEach(tool -> {
            Optional<Tool> result = repository.getToolById(tool.getId());

            // then
            assertNotNull(result.get(), "no tool returned for ID " + tool.getId());
        });
    }

    @Test
    public void shouldGetUtilityById() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getUtilities().isEmpty());

        // when
        repository.getUtilities().forEach(utility -> {
            Optional<Utility> result = repository.getUtilityById(utility.getId());

            // then
            assertTrue(result.isPresent(), "no utility returned for ID " + utility.getId());
        });
    }

    @Test
    public void shouldGetDownloadById() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

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
        repository.loadData();

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

                        assertEquals(HttpURLConnection.HTTP_OK, responseCode, "checked url: " + url.toExternalForm());
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
    public void shouldGetTutorialsByLibrary() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(library -> {
            List<Tutorial> list = repository.getTutorialsByModelObject(library);

            // then
            assertNotNull(list, "missing tutorials list for library " + library.getId());

            if (library.getId().equals("fxgl")) {
                assertTrue(!list.isEmpty(), "no tutorials returned for library fxgl");
            }
        });
    }

    @Test
    public void shouldGetVideosByLibrary() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(library -> {
            List<Video> list = repository.getVideosByModelObject(library);

            // then
            assertNotNull(list, "missing video list for library " + library.getId());

            if (library.getId().equals("tilesfx")) {
                assertTrue(!list.isEmpty(), "no videos returned for library tilesfx");
            }
        });
    }

    @Test
    public void shouldGetDownloadsByLibrary() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getLibraries().isEmpty());

        // when
        repository.getLibraries().forEach(library -> {
            List<Download> list = repository.getDownloadsByModelObject(library);

            // then
            assertNotNull(list, "missing downloads list for library " + library.getId());

            if (library.getId().equals("tilesfx")) {
                assertTrue(!list.isEmpty(), "no downloads returned for library tilesfx");
            }
        });
    }

    @Test
    public void shouldGetIkonliPacks() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        // when
        List<IkonliPack> ikonliPacks = repository.getIkonliPacks();

        // then
        assertFalse(ikonliPacks.isEmpty());
    }

    @Test
    public void shouldGetMemberById() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getMembers().isEmpty());

        // when
        repository.getMembers().forEach(member -> {
            Optional<Member> result = repository.getMemberById(member.getId());

            // then
            assertNotNull(result.get(), "no member returned for ID " + member.getId());
        });
    }

    @Test
    public void shouldGetDocumentationById() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        ObservableList<Documentation> documentation = repository.getDocumentation();
        assertFalse(documentation.isEmpty());

        // when
        documentation.forEach(doc -> {
            Optional<Documentation> result = repository.getDocumentationById(doc.getId());

            // then
            assertNotNull(result.get(), "no documentation returned for ID " + doc.getId());
        });
    }

    @Test
    public void shouldGetLearnJavaFXById() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        ObservableList<LearnJavaFX> learnJavaFX = repository.getLearnJavaFX();
        assertFalse(learnJavaFX.isEmpty());

        // when
        learnJavaFX.forEach(item -> {
            Optional<LearnJavaFX> result = repository.getLearnJavaFXById(item.getId());

            // then
            assertNotNull(result.get(), "no learnJavaFX returned for ID " + item.getId());
        });

        learnJavaFX.forEach(item -> {
            LearnJavaFX result = (LearnJavaFX) repository.getByID(LearnJavaFX.class, item.getId());

            // then
            assertNotNull(result, "no learnJavaFX returned for ID " + item.getId());
        });
    }

    @Test
    public void shouldLoadLearnJavaFXDescription() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getLearnJavaFX().isEmpty());

        // when
        repository.getLearnJavaFX().forEach(learnJavaFX -> {
            StringProperty text = repository.learnJavaFXReadMeProperty(learnJavaFX);

            // then
            assertNotNull(text.get(), "text missing for library ID " + learnJavaFX.getId());
        });
    }

    @Test
    public void shouldGetLearnMobileById(){
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        ObservableList<LearnMobile> learnMobile = repository.getLearnMobile();
        assertFalse(learnMobile.isEmpty());

        // when
        learnMobile.forEach(item -> {
            Optional<LearnMobile> result = repository.getLearnMobileById(item.getId());

            // then
            assertNotNull(result.get(), "no learnMobile returned for ID " + item.getId());
        });

        learnMobile.forEach(item -> {
            LearnMobile result = (LearnMobile) repository.getByID(LearnMobile.class, item.getId());

            // then
            assertNotNull(result, "no learnMobile returned for ID " + item.getId());
        });
    }

    @Test
    public void shouldLoadLearnMobileDescription() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getLearnMobile().isEmpty());

        // when
        repository.getLearnMobile().forEach(learnMobile -> {
            StringProperty text = repository.learnMobileReadMeProperty(learnMobile);

            // then
            assertNotNull(text.get(), "text missing for library ID " + learnMobile.getId());
        });
    }

    @Test
    public void shouldGetLearnRaspberryPiById() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        ObservableList<LearnRaspberryPi> learnRaspberryPi = repository.getLearnRaspberryPi();
        assertFalse(learnRaspberryPi.isEmpty());

        // when
        learnRaspberryPi.forEach(item -> {
            Optional<LearnRaspberryPi> result = repository.getLearnRaspberryPiById(item.getId());

            // then
            assertNotNull(result.get(), "no learnRaspberryPi returned for ID " + item.getId());
        });

        learnRaspberryPi.forEach(item -> {
            LearnRaspberryPi result = (LearnRaspberryPi) repository.getByID(LearnRaspberryPi.class, item.getId());

            // then
            assertNotNull(result, "no learnRaspberryPi returned for ID " + item.getId());
        });
    }

    @Test
    public void shouldLoadLearnRaspberryPiDescription() {
        // given
        DataRepository repository = DataRepository.getInstance();
        repository.loadData();

        assertFalse(repository.getLearnRaspberryPi().isEmpty());

        // when
        repository.getLearnRaspberryPi().forEach(learnRaspberryPi -> {
            StringProperty text = repository.learnRaspberryPiReadMeProperty(learnRaspberryPi);

            // then
            assertNotNull(text.get(), "text missing for library ID " + learnRaspberryPi.getId());
        });
    }

}
