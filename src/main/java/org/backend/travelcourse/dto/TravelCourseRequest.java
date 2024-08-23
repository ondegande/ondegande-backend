package org.backend.travelcourse.dto;

import org.backend.travelcourse.domain.TravelCourse;

public record TravelCourseRequest(
        String name,
        boolean isShared
) {

    public TravelCourse toEntity() {
        return new TravelCourse(
                name,
                isShared
        );
    }
}
