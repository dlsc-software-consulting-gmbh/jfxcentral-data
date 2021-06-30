package com.dlsc.jfxcentral.data.model;

import org.apache.commons.lang3.StringUtils;

public class Blog extends ModelObject {

    private String name;
    private String url;
    private String summary;
    private String companyId;
    private String feed;

    public Blog() {
    }

    @Override
    public boolean matches(String searchPattern) {
        if (tagsMatch(searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(summary, searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(name, searchPattern)) {
            return true;
        }

        return false;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }
}
