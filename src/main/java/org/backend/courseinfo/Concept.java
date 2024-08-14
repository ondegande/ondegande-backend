package org.backend.courseinfo;

public enum Concept {

    NATURE_HEALING("자연관광지"),

    CULTURE_HISTORY("역사관광지"),

    ACTIVITY_LEISURE("육상 레포츠"),

    GASTRONOMIC_TOUR("음식점"),

    FAMILY_TRIP("휴양관광지"),

    SOLO_HEALING("자연관광지"),

    URBAN_SHOPPING("쇼핑"),

    FESTIVAL_PERFORMANCE("축제");

    private final String mainCategory;

    Concept(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getMainCategory() {
        return mainCategory;
    }
}
