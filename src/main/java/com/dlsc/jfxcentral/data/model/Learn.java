package com.dlsc.jfxcentral.data.model;

import org.apache.commons.lang3.StringUtils;

public class Learn extends ModelObject {

    private String title;
    private String sourceCode;

    public Learn() {
    }

    @Override
    public boolean matches(String searchPattern) {
        if (super.matches(searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(title, searchPattern)) {
            return true;
        }

        return StringUtils.containsAnyIgnoreCase(sourceCode, searchPattern);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
}
