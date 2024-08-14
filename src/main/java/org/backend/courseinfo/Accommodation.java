package org.backend.courseinfo;

public enum Accommodation {

    HOTEL("관광호텔"),
    CONDOMINIUM("콘도미니엄"),
    YOUTH_HOSTEL("유스호스텔"),
    PENSION("펜션"),
    MOTEL("모텔"),
    GUESTHOUSE("게스트하우스"),
    SERVICED_RESIDENCE("서비스드레지던스");

    private final String name;

    Accommodation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
