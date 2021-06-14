package com.dlsc.jfxcentral.data.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Tutorial extends ModelObject {

    public enum Format {
        VIDEO,
        WEB
    }

    private String name;
    private String url;
    private boolean commercial;
    private String summary;
    private String companyId;
    private Format format;
    private List<String> personIds = new ArrayList<>();

    public Tutorial() {
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

    public Format getType() {
        return format;
    }

    public void setType(Format format) {
        this.format = format;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public List<String> getPersonIds() {
        return personIds;
    }

    public void setPersonIds(List<String> personIds) {
        this.personIds = personIds;
    }

    public boolean isCommercial() {
        return commercial;
    }

    public void setCommercial(boolean commercial) {
        this.commercial = commercial;
    }
}
