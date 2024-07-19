package org.backend.courseinfo;

public enum Schedule {

    ONE_NIGHTS_TWO_DAYS("1박 2일"),
    TWO_NIGHTS_THREE_DAYS("2박 3일"),
    THREE_NIGHTS_FOUR_DAYS("3박 4일"),
    FOUR_NIGHTS_FIVE_DAYS("4박 5일");

    private final String tripDays;

    Schedule(String tripDays) {
        this.tripDays = tripDays;
    }

    public String getTripDays() {
        return tripDays;
    }
}
