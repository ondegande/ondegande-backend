package org.backend.courseinfo;

public enum Companion {

    FAMILY("가족"),
    FRIEND("친구"),
    COUPLE("연인"),
    ALONE("혼자");

    private final String type;

    Companion(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }
}
