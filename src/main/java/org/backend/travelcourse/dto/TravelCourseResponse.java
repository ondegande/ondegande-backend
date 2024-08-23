package org.backend.travelcourse.dto;

import org.backend.travelcourse.domain.TravelCourse;

public record TravelCourseResponse(
        Long id,
        String name,
        boolean isShared
) {
    public static TravelCourseResponse toResponseDto(TravelCourse travelCourse) {
        return new TravelCourseResponse(
                travelCourse.getId(),
                travelCourse.getName(),
                travelCourse.isShared()
        );
    }
}
