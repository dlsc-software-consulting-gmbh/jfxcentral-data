package com.dlsc.jfxcentral.data.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Tool extends ModelObject implements Coordinates {

    private String name;
    private String summary;
    private String description;
    private String homepage;
    private String repository;
    private String groupId;
    private String artifactId;

    private List<String> tutorialIds = new ArrayList<>();
    private List<String> downloadIds = new ArrayList<>();
    private List<String> videoIds = new ArrayList<>();

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

        if (StringUtils.containsAnyIgnoreCase(description, searchPattern)) {
            return true;
        }

        return false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
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

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public List<String> getDownloadIds() {
        return downloadIds;
    }

    public void setDownloadIds(List<String> downloadIds) {
        this.downloadIds = downloadIds;
    }

    @Override
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public List<String> getVideoIds() {
        return videoIds;
    }

    public void setVideoIds(List<String> videoIds) {
        this.videoIds = videoIds;
    }

    public List<String> getTutorialIds() {
        return tutorialIds;
    }

    public void setTutorialIds(List<String> tutorialIds) {
        this.tutorialIds = tutorialIds;
    }
}
