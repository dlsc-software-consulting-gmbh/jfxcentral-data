package com.dlsc.jfxcentral.data.model;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;

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
