package com.dlsc.jfxcentral.data.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Book extends ModelObject {

    private String authors;
    private String title;
    private String subtitle;
    private String url;
    private String amazonASIN;
    private String isbn;
    private LocalDate publishedDate;
    private List<String> personIds = new ArrayList<>();

    public Book() {
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

    public List<String> getPersonIds() {
        return personIds;
    }

    public void setPersonIds(List<String> personIds) {
        this.personIds = personIds;
    }

    public String getAmazonASIN() {
        return amazonASIN;
    }

    public void setAmazonASIN(String amazonASIN) {
        this.amazonASIN = amazonASIN;
    }
}
