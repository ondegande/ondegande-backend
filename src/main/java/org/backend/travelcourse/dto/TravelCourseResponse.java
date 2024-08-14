package org.backend.travelcourse.dto;

import org.backend.courseinfo.Accommodation;
import org.backend.courseinfo.Concept;
import org.backend.courseinfo.Schedule;

public record TravelCourseResponse(
        Long id,
        Schedule schedule,
        Concept concept,
        Accommodation accommodation,
        boolean isShared
) {
}
