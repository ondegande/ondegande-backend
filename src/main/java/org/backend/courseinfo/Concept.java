package org.backend.courseinfo;

public enum Concept {

    FILIAL_DUTY("효도"),
    HEALING("힐링"),
    LEISURE("레저"),
    DRIVE("드라이브"),
    FOOD("먹거리"),
    NONE("선택안함");

    private final String type;

    Concept(String type) {
        this.type = type;
    }

    public String getName() {
        return type;
    }
}
