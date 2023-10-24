package com.dlsc.jfxcentral.data.model;

import org.apache.commons.lang3.StringUtils;

public class Learn extends ModelObject {

    private String sourceCodeUrl;
    private LearnType type;

    public Learn() {
    }

    @Override
    public boolean matches(String searchPattern) {
        if (super.matches(searchPattern)) {
            return true;
        }

        return StringUtils.containsAnyIgnoreCase(sourceCodeUrl, searchPattern);
    }

    public String getSourceCodeUrl() {
        return sourceCodeUrl;
    }

    public void setSourceCodeUrl(String sourceCodeUrl) {
        this.sourceCodeUrl = sourceCodeUrl;
    }

    public LearnType getType() {
        return type;
    }

    public void setType(LearnType type) {
        this.type = type;
    }
}
