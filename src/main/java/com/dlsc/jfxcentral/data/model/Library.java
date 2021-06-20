package com.dlsc.jfxcentral.data.model;

import org.apache.commons.lang3.StringUtils;

public class Library extends ModelObject implements Coordinates{

    private String title;
    private String summary;
    private String description;
    private String homepage;
    private String repository;
    private String documentation;
    private String issueTracker;
    private String discussionBoard;
    private String javadocs;
    private String groupId;
    private String artifactId;
    private String githubAccount;
    private String githubProject;

    private boolean ensemble;
    private boolean logoAvailable;

    private License license;

    public Library() {
    }

    @Override
    public boolean matches(String searchPattern) {
        if (tagsMatch(searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(summary, searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(title, searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(description, searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(groupId, searchPattern)) {
            return true;
        }

        if (StringUtils.containsAnyIgnoreCase(artifactId, searchPattern)) {
            return true;
        }

        return false;
    }

    public boolean isLogoAvailable() {
        return logoAvailable;
    }

    public void setLogoAvailable(boolean logoAvailable) {
        this.logoAvailable = logoAvailable;
    }

    public boolean isEnsemble() {
        return ensemble;
    }

    public void setEnsemble(boolean ensemble) {
        this.ensemble = ensemble;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getGithubAccount() {
        return githubAccount;
    }

    public void setGithubAccount(String githubAccount) {
        this.githubAccount = githubAccount;
    }

    public String getGithubProject() {
        return githubProject;
    }

    public void setGithubProject(String githubProject) {
        this.githubProject = githubProject;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getIssueTracker() {
        return issueTracker;
    }

    public void setIssueTracker(String issueTracker) {
        this.issueTracker = issueTracker;
    }

    public String getDiscussionBoard() {
        return discussionBoard;
    }

    public void setDiscussionBoard(String discussionBoard) {
        this.discussionBoard = discussionBoard;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String getJavadocs() {
        return javadocs;
    }

    public void setJavadocs(String javadocs) {
        this.javadocs = javadocs;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }
}
