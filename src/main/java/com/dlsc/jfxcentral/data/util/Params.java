
package com.dlsc.jfxcentral.data.util;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Params {

    @SerializedName("q")
    @Expose
    private String q;
    @SerializedName("core")
    @Expose
    private String core;
    @SerializedName("indent")
    @Expose
    private String indent;
    @SerializedName("spellcheck")
    @Expose
    private String spellcheck;
    @SerializedName("fl")
    @Expose
    private String fl;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("sort")
    @Expose
    private String sort;
    @SerializedName("spellcheck.count")
    @Expose
    private String spellcheckCount;
    @SerializedName("rows")
    @Expose
    private String rows;
    @SerializedName("wt")
    @Expose
    private String wt;
    @SerializedName("version")
    @Expose
    private String version;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public String getIndent() {
        return indent;
    }

    public void setIndent(String indent) {
        this.indent = indent;
    }

    public String getSpellcheck() {
        return spellcheck;
    }

    public void setSpellcheck(String spellcheck) {
        this.spellcheck = spellcheck;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSpellcheckCount() {
        return spellcheckCount;
    }

    public void setSpellcheckCount(String spellcheckCount) {
        this.spellcheckCount = spellcheckCount;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getWt() {
        return wt;
    }

    public void setWt(String wt) {
        this.wt = wt;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
