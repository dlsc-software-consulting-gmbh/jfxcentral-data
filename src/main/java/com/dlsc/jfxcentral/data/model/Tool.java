package com.dlsc.jfxcentral.data.model;

import java.util.ArrayList;
import java.util.List;

public class Tool extends ModelObject {

    private String name;
    private String summary;
    private String url;
    private List<Link> links = new ArrayList<>();

    public Tool() {
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
