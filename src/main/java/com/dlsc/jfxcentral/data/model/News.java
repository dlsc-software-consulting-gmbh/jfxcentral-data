package com.dlsc.jfxcentral.data.model;

import org.apache.commons.lang3.StringUtils;

public class News extends ModelObject {

    private String title;
    private String subtitle;
    private String author;

    private Type type = Type.MISC;

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
