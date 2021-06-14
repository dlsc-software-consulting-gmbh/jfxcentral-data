
package com.dlsc.jfxcentral.data.util;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Doc {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("g")
    @Expose
    private String g;
    @SerializedName("a")
    @Expose
    private String a;
    @SerializedName("latestVersion")
    @Expose
    private String latestVersion;
    @SerializedName("repositoryId")
    @Expose
    private String repositoryId;
    @SerializedName("p")
    @Expose
    private String p;
    @SerializedName("timestamp")
    @Expose
    private Long timestamp;
    @SerializedName("versionCount")
    @Expose
    private Integer versionCount;
    @SerializedName("text")
    @Expose
    private List<String> text = null;
    @SerializedName("ec")
    @Expose
    private List<String> ec = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getVersionCount() {
        return versionCount;
    }

    public void setVersionCount(Integer versionCount) {
        this.versionCount = versionCount;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public List<String> getEc() {
        return ec;
    }

    public void setEc(List<String> ec) {
        this.ec = ec;
    }

}
