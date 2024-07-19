package org.backend.courseinfo;

public enum Transport {

    CAR("자동차"),
    MOTORCYCLE("오토바이"),
    BICYCLE("자전거"),
    WALKING("도보"),
    NONE("선택안함");

    private final String type;

    Transport(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
