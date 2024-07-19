package org.backend.travelcourse.dto;

import org.backend.courseinfo.Accommodation;
import org.backend.courseinfo.Companion;
import org.backend.courseinfo.Concept;
import org.backend.courseinfo.Distance;
import org.backend.courseinfo.Schedule;
import org.backend.courseinfo.Season;
import org.backend.courseinfo.Transport;
import org.backend.travelcourse.domain.TravelCourse;

public record TravelCourseRequest(
        Schedule schedule,
        Season season,
        Companion companion,
        Concept concept,
        Transport transport,
        Distance distance,
        Accommodation accommodation,
        boolean isShared
) {

    public TravelCourse toEntity() {
        return new TravelCourse(
                schedule,
                season,
                companion,
                concept,
                transport,
                distance,
                accommodation,
                isShared
        );
    }
}
