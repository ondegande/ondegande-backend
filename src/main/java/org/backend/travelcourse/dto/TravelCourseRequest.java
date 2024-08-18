package org.backend.travelcourse.dto;

import org.backend.travelcourse.domain.Accommodation;
import org.backend.travelcourse.domain.Concept;
import org.backend.travelcourse.domain.Schedule;
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
