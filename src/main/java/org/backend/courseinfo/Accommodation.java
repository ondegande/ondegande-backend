package org.backend.courseinfo;

public enum Accommodation {

    CAMPING("캠핑"),
    CAR_CAMPINGI("차박"),
    PENSION("펜션"),
    HOTEL("호텔"),
    NONE("선택안함");

    private final String type;

    Accommodation(String type) {
        this.type = type;
    }

    public String getName() {
        return type;
    }
}
