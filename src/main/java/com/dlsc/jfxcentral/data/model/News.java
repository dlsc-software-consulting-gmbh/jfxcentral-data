package com.dlsc.jfxcentral.data.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class News extends ModelObject {

    private String title;
    private String subtitle;
    private String author;

    private Type type = Type.MISC;

    private List<String> personIds = new ArrayList<>();
    private List<String> libraryIds = new ArrayList<>();

    public enum Type {
        RELEASE,
        LINKS,
        EVENT,
        TIPS,
        NOTEWORTHY,
        MISC
    }

    public News() {
    }

    @Override
    public boolean matches(String searchPattern) {
        if (tagsMatch(searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(title, searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(subtitle, searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(author, searchPattern)) {
            return true;
        }

        return false;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<String> getLibraryIds() {
        return libraryIds;
    }

    public void setLibraryIds(List<String> libraryIds) {
        this.libraryIds = libraryIds;
    }

    public List<String> getPersonIds() {
        return personIds;
    }

    public void setPersonIds(List<String> personIds) {
        this.personIds = personIds;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
