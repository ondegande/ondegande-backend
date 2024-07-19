package org.backend.courseinfo;

public enum Distance {

    SHORTEST("최단기"),
    MODERATE("적당히"),
    FAR("멀리"),
    NONE("선택안함");

    private final String type;

    Distance(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
