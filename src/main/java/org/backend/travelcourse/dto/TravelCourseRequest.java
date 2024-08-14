package org.backend.travelcourse.dto;

import org.backend.courseinfo.Accommodation;
import org.backend.courseinfo.Concept;
import org.backend.courseinfo.Schedule;
import org.backend.travelcourse.domain.TravelCourse;

public record TravelCourseRequest(
        Schedule schedule,
        Concept concept,
        Accommodation accommodation,
        boolean isShared
) {

    public TravelCourse toEntity() {
        return new TravelCourse(
                schedule,
                concept,
                accommodation,
                isShared
        );
    }
}
