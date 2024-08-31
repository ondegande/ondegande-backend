package org.backend.travelcourse.dto;

import org.backend.travelcourse.domain.CreatorType;
import org.backend.travelcourse.domain.TravelCourse;

public record TravelCourseResponse(
        Long id,
        String courseName,
        boolean isShared,
        int days,
        String creatorName,
        CreatorType creatorType
) {
    public static TravelCourseResponse toResponseDto(TravelCourse travelCourse) {
        return new TravelCourseResponse(
                travelCourse.getTravelCourseId(),
                travelCourse.getCourseName(),
                travelCourse.isShared(),
                travelCourse.getDays(),
                travelCourse.getCreatorName(),
                travelCourse.getCreatorType()
        );
    }
}
