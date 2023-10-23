package com.dlsc.jfxcentral.data.model;

public class Utility extends ModelObject {

    private DevelopmentStatus status;
    private boolean nativeSupported;

    public Utility() {
    }

    public DevelopmentStatus getStatus() {
        return status;
    }

    public void setStatus(DevelopmentStatus status) {
        this.status = status;
    }

    public boolean isNativeSupported() {
        return nativeSupported;
    }

    public void setNativeSupported(boolean nativeSupported) {
        this.nativeSupported = nativeSupported;
    }
}
