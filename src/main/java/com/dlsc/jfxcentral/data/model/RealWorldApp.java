package com.dlsc.jfxcentral.data.model;

import org.apache.commons.lang3.StringUtils;

public class RealWorldApp extends ModelObject {

    private String company;
    private String domain;
    private String url;

    public RealWorldApp() {
    }

    @Override
    public boolean matches(String searchPattern) {
        if (super.matches(searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(company, searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(domain, searchPattern)) {
            return true;
        }

        return false;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
