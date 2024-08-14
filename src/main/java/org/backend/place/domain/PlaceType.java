package org.backend.place.domain;

public enum PlaceType {

    TOURIST_SPOT(12),
    CULTURAL_FACILITY(14),
    FESTIVAL_EVENT(15),
    TRAVEL_COURSE(25),
    LEISURE(28),
    ACCOMMODATION(32),
    SHOPPING(38),
    RESTAURANT(39);

    private final int contentTypeId;

    PlaceType(int contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    public int getContentTypeId() {
        return contentTypeId;
    }
}
