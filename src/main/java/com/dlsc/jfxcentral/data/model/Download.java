package com.dlsc.jfxcentral.data.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Download extends ModelObject {

    private String homepage;
    private DownloadType downloadType;
    private List<DownloadFile> files = new ArrayList<>();

    public enum DownloadType {
        APPLICATION,
        PRESENTATION,
        DOCUMENTATION
    }

    public enum FileType {
        DMG,
        PKG,
        MSI,
        ZIP,
        TXT,
        EXE,
        RPM,
        DEB,
        JAR
    }

    public static class DownloadFile {
        private String name;
        private String url;
        private String fileName;
        private FileType fileType;

        public DownloadFile() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public FileType getFileType() {
            return fileType;
        }

        public void setFileType(FileType fileType) {
            this.fileType = fileType;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }

    public Download() {
    }

    @Override
    public boolean matches(String searchPattern) {
        if (super.matches(searchPattern)) {
            return true;
        }

        for (DownloadFile file : getFiles()) {
            if (StringUtils.containsAnyIgnoreCase(file.getName(), searchPattern)) {
                return true;
            }
        }

        return false;
    }

    public DownloadType getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(DownloadType downloadType) {
        this.downloadType = downloadType;
    }

    public List<DownloadFile> getFiles() {
        return files;
    }

    public void setFiles(List<DownloadFile> files) {
        this.files = files;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
}
