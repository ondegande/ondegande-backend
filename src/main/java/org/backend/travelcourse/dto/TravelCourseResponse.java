package org.backend.travelcourse.dto;

import org.backend.travelcourse.domain.Accommodation;
import org.backend.travelcourse.domain.Concept;
import org.backend.travelcourse.domain.Schedule;
import org.backend.travelcourse.domain.TravelCourse;

public record TravelCourseResponse(
        Long id,
        Schedule schedule,
        Concept concept,
        Accommodation accommodation,
        boolean isShared
) {
    public static TravelCourseResponse toResponseDto(TravelCourse travelCourse) {
        return new TravelCourseResponse(
                travelCourse.getId(),
                travelCourse.getSchedule(),
                travelCourse.getConcept(),
                travelCourse.getAccommodation(),
                travelCourse.isShared()
        );
    }
}
