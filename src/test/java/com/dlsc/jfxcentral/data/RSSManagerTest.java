package com.dlsc.jfxcentral.data;

import com.dlsc.jfxcentral.data.model.LinksOfTheWeek;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class RSSManagerTest {

    private static final String SITE_URL = "https://www.jfx-central.com";
    private static final String FEED_URL = SITE_URL + "/lotw/rss.xml";
    private static final String LINKS_PAGE_URL = SITE_URL + "/links";
    private static final String ATOM_NAMESPACE = "http://www.w3.org/2005/Atom";
    private static final String DUBLIN_CORE_NAMESPACE = "http://purl.org/dc/elements/1.1/";
    private static final DateTimeFormatter TITLE_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @BeforeAll
    public static void setup() {
        DataRepository.setTesting(true);
    }

    private static Document parseXml(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        return factory.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
    }

    @Test
    void shouldCreateLinksOfTheWeekRss() throws Exception {
        // given
        DataRepository repository = DataRepository.getInstance();
        assertFalse(repository.getLinksOfTheWeek().isEmpty());

        List<LinksOfTheWeek> sortedLinksOfTheWeek = repository.getLinksOfTheWeek().stream()
                .sorted(Comparator.comparing(LinksOfTheWeek::getCreatedOn).reversed())
                .toList();

        // The RSS feed only contains the last 25 weeks, so pick the current (most recent)
        // week and one 20 weeks older, both of which are guaranteed to still be inside that window.
        LinksOfTheWeek currentWeek = sortedLinksOfTheWeek.get(0);
        LinksOfTheWeek currentWeekMinus20 = sortedLinksOfTheWeek.get(Math.min(20, sortedLinksOfTheWeek.size() - 1));

        String currentWeekTitle = "Links Of The Week - " + currentWeek.getCreatedOn().format(TITLE_DATE_FORMAT);
        String currentWeekMinus20Title = "Links Of The Week - " + currentWeekMinus20.getCreatedOn().format(TITLE_DATE_FORMAT);

        String rss = RSSManager.createRSS();
        System.out.println("RSS content:\n" + rss);

        Document document = parseXml(rss);
        Element atomLink = (Element) document.getElementsByTagNameNS(ATOM_NAMESPACE, "link").item(0);
        Element channelElement = (Element) document.getElementsByTagName("channel").item(0);
        Element channelLink = (Element) channelElement.getElementsByTagName("link").item(0);
        Element firstItem = (Element) document.getElementsByTagName("item").item(0);
        Element firstItemLink = (Element) firstItem.getElementsByTagName("link").item(0);
        Element firstItemGuid = (Element) firstItem.getElementsByTagName("guid").item(0);

        // then
        assertAll(
                () -> assertTrue(StringUtils.isNotBlank(rss), "RSS output with Links Of The Week is missing"),
                () -> assertTrue(rss.contains(currentWeekTitle), "Content from the current week is missing"),
                () -> assertTrue(rss.contains(currentWeekMinus20Title), "Content from 20 weeks ago is missing"),
                () -> assertTrue(rss.length() > 10_000, "RSS content length should be longer"),
                () -> assertEquals("rss", document.getDocumentElement().getTagName(), "Root element should be rss"),
                () -> assertNotNull(atomLink, "Atom self-link is missing"),
                () -> assertEquals(FEED_URL, atomLink.getAttribute("href"), "Atom self-link href should match feed URL"),
                () -> assertEquals("self", atomLink.getAttribute("rel"), "Atom self-link rel should be self"),
                () -> assertEquals("application/rss+xml", atomLink.getAttribute("type"), "Atom self-link type should match RSS media type"),
                () -> assertEquals(LINKS_PAGE_URL, channelLink.getTextContent(), "Channel link should use the canonical links page URL"),
                () -> assertTrue(firstItemLink.getTextContent().startsWith(LINKS_PAGE_URL), "Item link should start with the canonical links page URL"),
                () -> assertTrue( firstItemGuid.getTextContent().startsWith(LINKS_PAGE_URL), "Item guid should start with the canonical item URL"),
                () -> assertEquals(0, document.getElementsByTagNameNS(DUBLIN_CORE_NAMESPACE, "date").getLength(), "dc:date elements should be removed"),
                () -> assertFalse(rss.contains("https://jfx-central.com/lotw/rss.xml"), "RSS output should not contain the non-canonical feed URL"),
                () -> assertFalse(rss.contains("https://jfx-central.com/links"), "RSS output should not contain the non-canonical links page URL")
        );
    }
}
