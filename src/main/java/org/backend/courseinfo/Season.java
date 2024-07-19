package org.backend.courseinfo;

public enum Season {

    SPRING("봄"),
    SUMMER("여름"),
    AUTUMN("가을"),
    WINTER("겨울");

    private final String name;

    Season(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
