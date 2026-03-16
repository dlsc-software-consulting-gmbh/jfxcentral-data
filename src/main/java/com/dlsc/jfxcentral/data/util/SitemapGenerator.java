package com.dlsc.jfxcentral.data.util;

import com.dlsc.jfxcentral.data.DataRepository;
import com.dlsc.jfxcentral.data.model.LinksOfTheWeek;
import com.dlsc.jfxcentral.data.model.ModelObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Generates a sitemap.xml for https://www.jfx-central.com based on the
 * content available in the JFXCentral data repository.
 *
 * <p>Usage from code:
 * <pre>
 *   DataRepository.REPO_DIRECTORY = new File("/path/to/jfxcentral-data");
 *   String xml = SitemapGenerator.generate();
 *   SitemapGenerator.writeToFile(xml, Path.of("sitemap.xml"));
 * </pre>
 *
 * <p>Usage from the command line:
 * <pre>
 *   java -cp ... com.dlsc.jfxcentral.data.util.SitemapGenerator [repoDir] [outputFile]
 * </pre>
 * Both arguments are optional and default to the current working directory and
 * {@code sitemap.xml} respectively.
 */
public class SitemapGenerator {

    private static final String BASE_URL = "https://www.jfx-central.com";
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ISO_LOCAL_DATE;

    // Change frequencies
    private static final String WEEKLY = "weekly";
    private static final String MONTHLY = "monthly";

    // Priorities
    private static final double PRIORITY_HOME = 1.0;
    private static final double PRIORITY_CATEGORY = 0.9;
    private static final double PRIORITY_DETAIL = 0.8;
    private static final double PRIORITY_STATIC = 0.6;
    private static final double PRIORITY_LEGAL = 0.3;

    private SitemapGenerator() {
    }

    /**
     * Command-line entry point.
     *
     * @param args optional: [repoDir] [outputFile]
     */
    public static void main(String[] args) throws IOException {
        File repoDir = args.length > 0 ? new File(args[0]) : new File(System.getProperty("user.dir"));
        Path outputPath = args.length > 1 ? Path.of(args[1]) : Path.of("sitemap.xml");

        System.out.println("Repository : " + repoDir.getAbsolutePath());
        System.out.println("Output     : " + outputPath.toAbsolutePath());

        generate(repoDir, outputPath);

        System.out.println("Done — " + outputPath.toAbsolutePath());
    }

    /**
     * Generates the full sitemap XML string from the current DataRepository contents.
     */
    public static String generate() {
        DataRepository repo = DataRepository.getInstance();
        String today = LocalDate.now().format(DATE_FMT);

        StringBuilder sb = new StringBuilder(65_536);
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");

        // Home
        addUrl(sb, "/", today, WEEKLY, PRIORITY_HOME);

        // Category pages
        for (String path : categoryPaths()) {
            addUrl(sb, path, today, WEEKLY, PRIORITY_CATEGORY);
        }

        // Static pages
        addUrl(sb, "/credits",       today, MONTHLY, PRIORITY_STATIC);
        addUrl(sb, "/team",          today, MONTHLY, PRIORITY_STATIC);
        addUrl(sb, "/openjfx",       today, MONTHLY, PRIORITY_STATIC);
        addUrl(sb, "/legal/terms",   today, MONTHLY, PRIORITY_LEGAL);
        addUrl(sb, "/legal/cookies", today, MONTHLY, PRIORITY_LEGAL);
        addUrl(sb, "/legal/privacy", today, MONTHLY, PRIORITY_LEGAL);

        // Detail pages — standard category/id pattern
        addDetailUrls(sb, repo.getBlogs(),             "/blogs",             today);
        addDetailUrls(sb, repo.getBooks(),             "/books",             today);
        addDetailUrls(sb, repo.getCompanies(),         "/companies",         today);
        addDetailUrls(sb, repo.getDownloads(),         "/downloads",         today);
        addDetailUrls(sb, repo.getLibraries(),         "/libraries",         today);
        addDetailUrls(sb, repo.getPeople(),            "/people",            today);
        addDetailUrls(sb, repo.getRealWorldApps(),     "/showcases",         today);
        addDetailUrls(sb, repo.getTips(),              "/tips",              today);
        addDetailUrls(sb, repo.getTools(),             "/tools",             today);
        addDetailUrls(sb, repo.getTutorials(),         "/tutorials",         today);
        addDetailUrls(sb, repo.getVideos(),            "/videos",            today);
        addDetailUrls(sb, repo.getUtilities(),         "/utilities",         today);
        addDetailUrls(sb, repo.getLearnJavaFX(),       "/learn-javafx",      today);
        addDetailUrls(sb, repo.getLearnMobile(),       "/learn-mobile",      today);
        addDetailUrls(sb, repo.getLearnRaspberryPi(),  "/learn-raspberrypi", today);
        addDetailUrls(sb, repo.getIkonliPacks(),       "/icons",             today);

        // Links of the week — ID format is "YYYY/YYYY-MM/YYYY-MM-DD"; URL uses only the date segment
        for (LinksOfTheWeek lotw : repo.getLinksOfTheWeek()) {
            if (lotw.isHide()) {
                continue;
            }
            String[] parts = lotw.getId().split("/");
            String dateSegment = parts[parts.length - 1];
            addUrl(sb, "/links/" + dateSegment, lastmod(lotw, today), MONTHLY, PRIORITY_DETAIL);
        }

        sb.append("</urlset>\n");
        return sb.toString();
    }

    /**
     * Writes the sitemap XML to the given file path (UTF-8).
     */
    public static void writeToFile(String xml, Path outputPath) throws IOException {
        Files.writeString(outputPath, xml, StandardCharsets.UTF_8);
    }

    /**
     * Convenience method: sets the repository directory, reloads data, generates
     * the sitemap, and writes it to {@code outputPath}.
     *
     * @param repoDirectory the root of the jfxcentral-data repository
     * @param outputPath    destination file (e.g. {@code Path.of("sitemap.xml")})
     */
    public static void generate(File repoDirectory, Path outputPath) throws IOException {
        DataRepository.REPO_DIRECTORY = repoDirectory.getAbsoluteFile();
        DataRepository.getInstance().reload();
        writeToFile(generate(), outputPath);
    }

    // --- private helpers ---

    private static List<String> categoryPaths() {
        return List.of(
                "/blogs",
                "/books",
                "/companies",
                "/downloads",
                "/libraries",
                "/people",
                "/showcases",
                "/tips",
                "/tools",
                "/tutorials",
                "/videos",
                "/utilities",
                "/learn-javafx",
                "/learn-mobile",
                "/learn-raspberrypi",
                "/icons",
                "/links",
                "/documentation"
        );
    }

    private static <T extends ModelObject> void addDetailUrls(
            StringBuilder sb, List<T> items, String basePath, String today) {
        for (T item : items) {
            if (item.isHide()) {
                continue;
            }
            addUrl(sb, basePath + "/" + item.getId(), lastmod(item, today), MONTHLY, PRIORITY_DETAIL);
        }
    }

    private static String lastmod(ModelObject obj, String fallback) {
        LocalDate date = obj.getModifiedOn();
        if (date == null) {
            date = obj.getCreatedOn();
        }
        return date != null ? date.format(DATE_FMT) : fallback;
    }

    private static void addUrl(StringBuilder sb, String path, String lastmod,
                               String changefreq, double priority) {
        sb.append("  <url>\n");
        sb.append("    <loc>").append(BASE_URL).append(path).append("</loc>\n");
        sb.append("    <lastmod>").append(lastmod).append("</lastmod>\n");
        sb.append("    <changefreq>").append(changefreq).append("</changefreq>\n");
        sb.append("    <priority>").append(String.format("%.1f", priority)).append("</priority>\n");
        sb.append("  </url>\n");
    }
}
