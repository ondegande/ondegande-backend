package org.backend.travelcourse.dto;

import org.backend.travelcourse.domain.CreatorType;
import org.backend.travelcourse.domain.TravelCourse;

public record TravelCourseRequest(
        String courseName,
        boolean isShared,
        int days,
        String creatorName,
        CreatorType creatorType
) {

    public TravelCourse toEntity() {
        return new TravelCourse(
                courseName,
                isShared,
                days,
                creatorName,
                creatorType
        );
    }
}
