package com.dlsc.jfxcentral.data.model;

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