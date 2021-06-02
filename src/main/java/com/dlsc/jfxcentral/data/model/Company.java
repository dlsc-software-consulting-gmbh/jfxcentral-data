package com.dlsc.jfxcentral.data.model;

import java.util.ArrayList;
import java.util.List;

public class Company extends ModelObject {

    private String name;
    private String homepage;
    private boolean consulting;
    private boolean freelancing;
    private List<String> libraryIds = new ArrayList<>();

    public Company() {
    }

    public boolean isConsulting() {
        return consulting;
    }

    public void setConsulting(boolean consulting) {
        this.consulting = consulting;
    }

    public boolean isFreelancing() {
        return freelancing;
    }

    public void setFreelancing(boolean freelancing) {
        this.freelancing = freelancing;
    }

    public List<String> getLibraryIds() {
        return libraryIds;
    }

    public void setLibraryIds(List<String> libraryIds) {
        this.libraryIds = libraryIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
}
