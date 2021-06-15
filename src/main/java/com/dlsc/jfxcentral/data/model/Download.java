package com.dlsc.jfxcentral.data.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Download extends ModelObject {

    private String title;
    private String homepage;
    private DownloadType downloadType;
    private List<String> personIds = new ArrayList<>();
    private List<String> companyIds = new ArrayList<>();
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
        EXE
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
        if (tagsMatch(searchPattern)) {
            return true;
        }


        if (StringUtils.containsAnyIgnoreCase(title, searchPattern)) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DownloadFile> getFiles() {
        return files;
    }

    public void setFiles(List<DownloadFile> files) {
        this.files = files;
    }

    public List<String> getPersonIds() {
        return personIds;
    }

    public void setPersonIds(List<String> personIds) {
        this.personIds = personIds;
    }

    public List<String> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<String> companyIds) {
        this.companyIds = companyIds;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
}
