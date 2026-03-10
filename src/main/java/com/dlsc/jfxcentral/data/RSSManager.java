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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class RSSManager {

    private static final Logger LOG = Logger.getLogger(RSSManager.class.getName());

    private static final String SITE_URL = "https://www.jfx-central.com";
    private static final String FEED_URL = SITE_URL + "/lotw/rss.xml";
    private static final String LINKS_PAGE_URL = SITE_URL + "/links";
    private static final String ATOM_NAMESPACE = "http://www.w3.org/2005/Atom";
    private static final String DUBLIN_CORE_NAMESPACE = "http://purl.org/dc/elements/1.1/";
    private static final DateTimeFormatter TITLE_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter PATH_DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final Pattern WWW_LINK_PATTERN = Pattern.compile("(?i)(<(?:a|img)\\b[^>]*\\s(?:href|src)=\")www\\.");

    public static String createRSS() {
        DataRepository repository = DataRepository.getInstance();
        List<LinksOfTheWeek> links = repository.getLinksOfTheWeek();

        // Based on the example provided on
        // https://rometools.github.io/rome/RssAndAtOMUtilitiEsROMEV0.5AndAboveTutorialsAndArticles/RssAndAtOMUtilitiEsROMEV0.5TutorialUsingROMEToCreateAndWriteASyndicationFeed.html

        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("rss_2.0");
        feed.setTitle("JFX-Central Links Of The Week");
        feed.setDescription("Your weekly update on all-things-JavaFX");
        feed.setLink(LINKS_PAGE_URL);
        feed.setUri(FEED_URL);

        List<SyndEntry> entries = new ArrayList<>();
        feed.setEntries(entries);

        List<LinksOfTheWeek> allLinksOfTheWeek = links.stream()
                .sorted(Comparator.comparing(LinksOfTheWeek::getCreatedOn).reversed())
                .limit(25) // Last 25 weeks
                .toList();

        for (LinksOfTheWeek linksOfTheWeek : allLinksOfTheWeek) {
            SyndContentImpl description = new SyndContentImpl();
            description.setType("text/html");
            description.setValue(getLinksOfTheWeekAsHtml(repository, linksOfTheWeek));

            String itemUrl = LINKS_PAGE_URL + "/"
                    + linksOfTheWeek.getCreatedOn().format(PATH_DATE_FORMAT);

            SyndEntry entry = new SyndEntryImpl();
            entry.setTitle("Links Of The Week - " + linksOfTheWeek.getCreatedOn().format(TITLE_DATE_FORMAT));
            entry.setLink(itemUrl);
            entry.setUri(itemUrl);
            entry.setPublishedDate(DateUtils.asDate(linksOfTheWeek.getCreatedOn()));
            entry.setDescription(description);
            entries.add(entry);
        }

        try {
            return postProcessRss(new SyndFeedOutput().outputString(feed));
        } catch (Exception e) {
            LOG.severe("Feed could not be generated: " + e.getMessage());
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
            String html = renderer.render(parser.parse(markdownContent.get()));
            return normalizeRelativeUrls(html);
        } catch (Exception e) {
            LOG.severe("Error while rendering markdown content: " + e.getMessage());
        }
        return "";
    }

    private static String postProcessRss(String rss) throws Exception {
        Document document = parseXml(rss);
        Element rssElement = document.getDocumentElement();
        Element channelElement = (Element) document.getElementsByTagName("channel").item(0);

        if (rssElement == null || channelElement == null) {
            throw new FeedException("Generated RSS is missing the rss/channel structure");
        }

        removeElementsByNamespace(document, DUBLIN_CORE_NAMESPACE, "date");
        rssElement.removeAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, "dc");
        rssElement.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, "xmlns:atom", ATOM_NAMESPACE);
        removeElementsByNamespace(channelElement, ATOM_NAMESPACE, "link");
        insertAtomSelfLink(document, channelElement);

        return toXml(document);
    }

    private static Document parseXml(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        return factory.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
    }

    private static void removeElementsByNamespace(Node scope, String namespace, String localName) {
        NodeList elements = scope instanceof Document document
                ? document.getElementsByTagNameNS(namespace, localName)
                : ((Element) scope).getElementsByTagNameNS(namespace, localName);

        while (elements.getLength() > 0) {
            Node node = elements.item(0);
            node.getParentNode().removeChild(node);
        }
    }

    private static void insertAtomSelfLink(Document document, Element channelElement) {
        Element atomLink = document.createElementNS(ATOM_NAMESPACE, "atom:link");
        atomLink.setAttribute("href", FEED_URL);
        atomLink.setAttribute("rel", "self");
        atomLink.setAttribute("type", "application/rss+xml");
        channelElement.insertBefore(atomLink, channelElement.getFirstChild());
    }

    private static String toXml(Document document) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");

        var transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");

        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(writer));
        return writer.toString();
    }

    private static String normalizeRelativeUrls(String html) {
        return WWW_LINK_PATTERN.matcher(html).replaceAll("$1https://");
    }
}
