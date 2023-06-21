package com.dlsc.jfxcentral.data.model;

public class Member extends ModelObject {

    private String jobTitle;
    private String email;
    private String website;
    private String linkedIn;
    private String twitter;
    private String gitHub;
    private boolean champion;
    private boolean rockstar;

    public Member() {
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getGitHub() {
        return gitHub;
    }

    public void setGitHub(String gitHub) {
        this.gitHub = gitHub;
    }

    public boolean isChampion() {
        return champion;
    }

    public void setChampion(boolean champion) {
        this.champion = champion;
    }

    public boolean isRockstar() {
        return rockstar;
    }

    public void setRockstar(boolean rockstar) {
        this.rockstar = rockstar;
    }
}
