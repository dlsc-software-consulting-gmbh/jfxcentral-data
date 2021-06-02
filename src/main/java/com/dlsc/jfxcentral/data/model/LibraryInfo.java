package com.dlsc.jfxcentral.data.model;

import java.util.ArrayList;
import java.util.List;

public class LibraryInfo extends ModelObject {

    private String libraryId;

    private List<Image> images = new ArrayList<>();

    private List<Video> videos = new ArrayList<>();

    public LibraryInfo() {
    }

    public String getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
