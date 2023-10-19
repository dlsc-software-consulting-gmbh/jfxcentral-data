package com.dlsc.jfxcentral.data.model;

import org.apache.commons.lang3.StringUtils;

public class Learn extends ModelObject {

    private String sourceCodeUrl;

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

    public enum LearnType {
        JAVA_FX("javafx"),
        MOBILE("mobile"),
        RASPBERRY_PI("raspberrypi");

        private final String directory;

        LearnType(String directory) {
            this.directory = directory;
        }

        public String getDirectory() {
            return directory;
        }
    }
}
