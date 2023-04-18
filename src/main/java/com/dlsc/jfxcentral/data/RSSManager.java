package com.dlsc.jfxcentral.data;

import com.dlsc.jfxcentral.data.model.LinksOfTheWeek;
import com.dlsc.jfxcentral.data.util.DateUtils;
import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RSSManager {

    private static final Logger LOG = Logger.getLogger(RSSManager.class.getName());

    public static String createRSS() {
        DataRepository repository = DataRepository.getInstance();
        List<LinksOfTheWeek> links = repository.getLinksOfTheWeek();

        // Based on the example provided on
        // https://rometools.github.io/rome/RssAndAtOMUtilitiEsROMEV0.5AndAboveTutorialsAndArticles/RssAndAtOMUtilitiEsROMEV0.5TutorialUsingROMEToCreateAndWriteASyndicationFeed.html
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("rss_2.0");
        feed.setTitle("JFX-Central Links Of The Week");
        feed.setDescription("Your weekly update on all-things-JavaFX");
        feed.setLink("https://jfx-central.com/rss/linksoftheweek");

        List<SyndEntry> entries = new ArrayList<>();
        feed.setEntries(entries);

        for (var linksOfTheWeek : links.stream()
                .sorted(Comparator.comparing(LinksOfTheWeek::getCreatedOn).reversed())
                .collect(Collectors.toList())) {
            SyndEntry entry = new SyndEntryImpl();
            entry.setTitle("Links Of The Week - " + linksOfTheWeek.getCreatedOn().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            entry.setLink("https://jfx-central.com"); // TODO link to correct page with these LOTW on jfxcentral2
            entry.setPublishedDate(DateUtils.asDate(linksOfTheWeek.getCreatedOn()));
            var description = new SyndContentImpl();
            description.setType("text/html");
            description.setValue(getLinksOfTheWeekAsHtml(repository, linksOfTheWeek));
            entry.setDescription(description);
            entries.add(entry);
        }

        try {
            return new SyndFeedOutput().outputString(feed);
        } catch (FeedException e) {
            e.printStackTrace();
        }

        return "";
    }

    private static String getLinksOfTheWeekAsHtml(DataRepository repository, LinksOfTheWeek linksOfTheWeek) {
        StringProperty markdownContent = new SimpleStringProperty();
        repository.loadLinksOfTheWeekText(linksOfTheWeek, markdownContent);

        if (markdownContent.get() == null || markdownContent.get().isEmpty()) {
            LOG.warning("Markdown content not provided");
            return "";
        }
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        try {
            return renderer.render(parser.parse(markdownContent.get()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
