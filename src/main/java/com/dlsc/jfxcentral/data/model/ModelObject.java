package com.dlsc.jfxcentral.data.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public abstract class ModelObject {

    private String id;
    private LocalDate createdOn;
    private LocalDate modifiedOn;
    private String tags;
    private boolean hide;

    private List<String> personIds = new ArrayList<>();
    private List<String> tutorialIds = new ArrayList<>();
    private List<String> toolIds = new ArrayList<>();
    private List<String> libraryIds = new ArrayList<>();
    private List<String> bookIds = new ArrayList<>();
    private List<String> companyIds = new ArrayList<>();
    private List<String> downloadIds = new ArrayList<>();
    private List<String> videoIds = new ArrayList<>();
    private List<String> appIds = new ArrayList<>();
    private List<String> blogIds = new ArrayList<>();

    protected ModelObject() {
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public boolean matches(String searchPattern) {
        return false;
    }

    protected boolean tagsMatch(String searchPattern) {
        if (StringUtils.isNotBlank(tags)) {
            StringTokenizer st = new StringTokenizer(getTags(), ",");
            while (st.hasMoreTokens()) {
                String tag = st.nextToken().trim();
                if (StringUtils.containsAnyIgnoreCase(tag, searchPattern)) {
                    return true;
                }
            }
        }

        return false;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDate getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDate modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ModelObject that = (ModelObject) o;

        return new EqualsBuilder().append(id, that.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }

    public List<String> getPersonIds() {
        return personIds;
    }

    public void setPersonIds(List<String> personIds) {
        this.personIds = personIds;
    }

    public List<String> getTutorialIds() {
        return tutorialIds;
    }

    public void setTutorialIds(List<String> tutorialIds) {
        this.tutorialIds = tutorialIds;
    }

    public List<String> getToolIds() {
        return toolIds;
    }

    public void setToolIds(List<String> toolIds) {
        this.toolIds = toolIds;
    }

    public List<String> getLibraryIds() {
        return libraryIds;
    }

    public void setLibraryIds(List<String> libraryIds) {
        this.libraryIds = libraryIds;
    }

    public List<String> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<String> bookIds) {
        this.bookIds = bookIds;
    }

    public List<String> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<String> companyIds) {
        this.companyIds = companyIds;
    }

    public List<String> getDownloadIds() {
        return downloadIds;
    }

    public void setDownloadIds(List<String> downloadIds) {
        this.downloadIds = downloadIds;
    }

    public List<String> getVideoIds() {
        return videoIds;
    }

    public void setVideoIds(List<String> videoIds) {
        this.videoIds = videoIds;
    }

    public List<String> getAppIds() {
        return appIds;
    }

    public void setAppIds(List<String> appIds) {
        this.appIds = appIds;
    }

    public List<String> getNewsIds() {
        return newsIds;
    }

    public void setNewsIds(List<String> newsIds) {
        this.newsIds = newsIds;
    }

    private List<String> newsIds = new ArrayList<>();

    public List<String> getBlogIds() {
        return blogIds;
    }

    public void setBlogIds(List<String> blogIds) {
        this.blogIds = blogIds;
    }
}
