package com.dlsc.jfxcentral.data.model;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Post extends ModelObject {

    private final Blog blog;
    private final SyndFeed syndFeed;
    private final SyndEntry syndEntry;

    public Post(Blog blog, SyndFeed syndFeed, SyndEntry syndEntry) {
        this.blog = blog;
        this.syndFeed = syndFeed;
        this.syndEntry = syndEntry;
    }

    @Override
    public boolean matches(String searchPattern) {
        if (super.matches(searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(syndEntry.getTitle(), searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(syndEntry.getAuthor(), searchPattern)) {
            return true;
        }

        return false;
    }

    public Blog getBlog() {
        return blog;
    }

    public SyndFeed getSyndFeed() {
        return syndFeed;
    }

    public SyndEntry getSyndEntry() {
        return syndEntry;
    }

    public LocalDate getDate() {
        Date date = syndEntry.getUpdatedDate();
        if (date == null) {
            date = syndEntry.getPublishedDate();
        }

        if (date != null) {
            return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
        }

        return LocalDate.now();
    }
}
