package com.dlsc.jfxcentral.data.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Tool extends ModelObject {

    private String name;
    private String summary;
    private String url;
    private List<Link> links = new ArrayList<>();

    public Tool() {
    }

    @Override
    public boolean matches(String searchPattern) {
        if (tagsMatch(searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(name, searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(summary, searchPattern)) {
            return true;
        }

        return false;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
