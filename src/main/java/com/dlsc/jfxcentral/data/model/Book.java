package com.dlsc.jfxcentral.data.model;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

public class Book extends ModelObject {

    private String summary;
    private String authors;
    private String publisher;
    private String title;
    private String subtitle;
    private String url;
    private String amazonASIN;
    private String isbn;

    private LocalDate publishedDate;

    public Book() {
    }

    @Override
    public boolean matches(String searchPattern) {
        if (tagsMatch(searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(authors, searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(title, searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(subtitle, searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(publisher, searchPattern)) {
            return true;
        }

        return false;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getAmazonASIN() {
        return amazonASIN;
    }

    public void setAmazonASIN(String amazonASIN) {
        this.amazonASIN = amazonASIN;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
