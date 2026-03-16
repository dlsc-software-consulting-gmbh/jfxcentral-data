package com.dlsc.jfxcentral.data.util;

import com.dlsc.jfxcentral.data.DataRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class SitemapGeneratorTest {

    private static final String BASE_URL = "https://www.jfx-central.com";
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ISO_LOCAL_DATE;

    @BeforeAll
    static void setup() {
        DataRepository.setTesting(true);
        DataRepository.getInstance().reload();
    }

    // --- XML structure ---

    @Test
    void xmlShouldStartWithDeclaration() {
        String xml = SitemapGenerator.generate();
        assertTrue(xml.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"),
                "Should start with XML declaration");
    }

    @Test
    void xmlShouldContainUrlsetElement() {
        String xml = SitemapGenerator.generate();
        assertTrue(xml.contains("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">"),
                "Should contain <urlset> with correct namespace");
        assertTrue(xml.endsWith("</urlset>\n"), "Should close with </urlset>");
    }

    @Test
    void everyUrlBlockShouldHaveRequiredChildren() {
        String xml = SitemapGenerator.generate();
        Pattern block = Pattern.compile("<url>\\s*(.*?)\\s*</url>", Pattern.DOTALL);
        Matcher m = block.matcher(xml);
        int count = 0;
        while (m.find()) {
            String urlBlock = m.group(1);
            assertTrue(urlBlock.contains("<loc>"),        "<url> missing <loc>");
            assertTrue(urlBlock.contains("<lastmod>"),    "<url> missing <lastmod>");
            assertTrue(urlBlock.contains("<changefreq>"), "<url> missing <changefreq>");
            assertTrue(urlBlock.contains("<priority>"),   "<url> missing <priority>");
            count++;
        }
        assertTrue(count > 0, "No <url> blocks found");
    }

    @Test
    void lastmodDatesShouldBeIso8601() {
        String xml = SitemapGenerator.generate();
        Pattern p = Pattern.compile("<lastmod>(.*?)</lastmod>");
        Matcher m = p.matcher(xml);
        while (m.find()) {
            String date = m.group(1);
            assertDoesNotThrow(() -> LocalDate.parse(date, DATE_FMT),
                    () -> "Invalid lastmod date: " + date);
        }
    }

    // --- Fixed URLs ---

    @Test
    void shouldContainHomeUrl() {
        String xml = SitemapGenerator.generate();
        assertUrlPresent(xml, "/", "1.0", "weekly");
    }

    @Test
    void shouldContainAllCategoryUrls() {
        String xml = SitemapGenerator.generate();
        List<String> categories = List.of(
                "/blogs", "/books", "/companies", "/downloads", "/libraries",
                "/people", "/showcases", "/tips", "/tools", "/tutorials",
                "/videos", "/utilities", "/learn-javafx", "/learn-mobile",
                "/learn-raspberrypi", "/icons", "/links", "/documentation"
        );
        for (String path : categories) {
            assertUrlPresent(xml, path, "0.9", "weekly");
        }
    }

    @Test
    void shouldContainStaticPages() {
        String xml = SitemapGenerator.generate();
        assertUrlPresent(xml, "/credits", "0.6", "monthly");
        assertUrlPresent(xml, "/team",    "0.6", "monthly");
        assertUrlPresent(xml, "/openjfx", "0.6", "monthly");
    }

    @Test
    void shouldContainLegalPages() {
        String xml = SitemapGenerator.generate();
        assertUrlPresent(xml, "/legal/terms",   "0.3", "monthly");
        assertUrlPresent(xml, "/legal/cookies", "0.3", "monthly");
        assertUrlPresent(xml, "/legal/privacy", "0.3", "monthly");
    }

    // --- Detail URLs ---

    @Test
    void shouldContainLibraryDetailUrls() {
        String xml = SitemapGenerator.generate();
        DataRepository.getInstance().getLibraries().stream()
                .filter(l -> !l.isHide())
                .forEach(lib -> assertUrlPresent(xml, "/libraries/" + lib.getId(), "0.8", "monthly"));
    }

    @Test
    void shouldContainPeopleDetailUrls() {
        String xml = SitemapGenerator.generate();
        DataRepository.getInstance().getPeople().stream()
                .filter(p -> !p.isHide())
                .forEach(p -> assertUrlPresent(xml, "/people/" + p.getId(), "0.8", "monthly"));
    }

    @Test
    void shouldContainBlogDetailUrls() {
        String xml = SitemapGenerator.generate();
        DataRepository.getInstance().getBlogs().stream()
                .filter(b -> !b.isHide())
                .forEach(b -> assertUrlPresent(xml, "/blogs/" + b.getId(), "0.8", "monthly"));
    }

    @Test
    void shouldContainToolDetailUrls() {
        String xml = SitemapGenerator.generate();
        DataRepository.getInstance().getTools().stream()
                .filter(t -> !t.isHide())
                .forEach(t -> assertUrlPresent(xml, "/tools/" + t.getId(), "0.8", "monthly"));
    }

    @Test
    void shouldContainRealWorldAppDetailUrls() {
        String xml = SitemapGenerator.generate();
        DataRepository.getInstance().getRealWorldApps().stream()
                .filter(a -> !a.isHide())
                .forEach(a -> assertUrlPresent(xml, "/showcases/" + a.getId(), "0.8", "monthly"));
    }

    // --- Links of the Week ---

    @Test
    void linksOfTheWeekShouldUseDateSegmentOnly() {
        String xml = SitemapGenerator.generate();
        DataRepository.getInstance().getLinksOfTheWeek().stream()
                .filter(l -> !l.isHide())
                .forEach(l -> {
                    String[] parts = l.getId().split("/");
                    String dateSegment = parts[parts.length - 1];
                    // The full compound ID must NOT appear in the URL
                    String wrongUrl = BASE_URL + "/links/" + l.getId();
                    assertFalse(xml.contains("<loc>" + wrongUrl + "</loc>"),
                            "LOTW URL must not contain compound ID: " + l.getId());
                    // Only the date segment should be used
                    assertUrlPresent(xml, "/links/" + dateSegment, "0.8", "monthly");
                });
    }

    // --- Hidden entities ---

    @Test
    void hiddenEntitiesShouldBeExcluded() {
        String xml = SitemapGenerator.generate();
        DataRepository repo = DataRepository.getInstance();

        repo.getLibraries().stream()
                .filter(l -> l.isHide())
                .forEach(l -> assertFalse(
                        xml.contains("<loc>" + BASE_URL + "/libraries/" + l.getId() + "</loc>"),
                        "Hidden library should not appear: " + l.getId()));

        repo.getPeople().stream()
                .filter(p -> p.isHide())
                .forEach(p -> assertFalse(
                        xml.contains("<loc>" + BASE_URL + "/people/" + p.getId() + "</loc>"),
                        "Hidden person should not appear: " + p.getId()));
    }

    // --- News must be excluded ---

    @Test
    void newsShouldNotBeIncluded() {
        String xml = SitemapGenerator.generate();
        DataRepository.getInstance().getNews().forEach(n -> assertFalse(
                xml.contains("<loc>" + BASE_URL + "/news/" + n.getId() + "</loc>"),
                "News entry should not appear in sitemap: " + n.getId()));
    }

    // --- File I/O ---

    @Test
    void writeToFileShouldCreateFile(@TempDir Path tmp) throws IOException {
        String xml = SitemapGenerator.generate();
        Path out = tmp.resolve("sitemap.xml");
        SitemapGenerator.writeToFile(xml, out);

        assertTrue(Files.exists(out), "Output file should exist");
        String written = Files.readString(out);
        assertEquals(xml, written, "File content should match generated XML");
    }

    @Test
    void generateWithFileAndPathShouldCreateSitemapFile(@TempDir Path tmp) throws IOException {
        Path out = tmp.resolve("sitemap.xml");
        File repoDir = DataRepository.getRepositoryDirectory();

        SitemapGenerator.generate(repoDir, out);

        assertTrue(Files.exists(out), "Sitemap file should be created");
        String content = Files.readString(out);
        assertTrue(content.startsWith("<?xml"), "Written file should be valid XML");
        assertTrue(content.contains("<urlset"), "Written file should contain urlset");
    }

    // --- Helper ---

    /**
     * Asserts that the sitemap XML contains a {@code <url>} block for the given path
     * with the expected priority and changefreq.
     */
    private static void assertUrlPresent(String xml, String path, String priority, String changefreq) {
        String loc = "<loc>" + BASE_URL + path + "</loc>";
        assertTrue(xml.contains(loc), "Expected URL in sitemap: " + path);

        int locIdx = xml.indexOf(loc);
        int blockStart = xml.lastIndexOf("<url>", locIdx);
        int blockEnd = xml.indexOf("</url>", locIdx) + "</url>".length();
        String block = xml.substring(blockStart, blockEnd);

        assertTrue(block.contains("<priority>" + priority + "</priority>"),
                "Wrong priority for " + path + ". Expected " + priority + " in:\n" + block);
        assertTrue(block.contains("<changefreq>" + changefreq + "</changefreq>"),
                "Wrong changefreq for " + path + ". Expected " + changefreq + " in:\n" + block);
    }
}
