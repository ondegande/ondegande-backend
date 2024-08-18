package org.backend.travelcourse.domain;

public enum Schedule {

    ONE_NIGHTS("당일치기"),
    ONE_NIGHTS_TWO_DAYS("1박 2일"),
    TWO_NIGHTS_THREE_DAYS("2박 3일");

    private final String tripDays;

    Schedule(String tripDays) {
        this.tripDays = tripDays;
    }

    public String getTripDays() {
        return tripDays;
    }
}
