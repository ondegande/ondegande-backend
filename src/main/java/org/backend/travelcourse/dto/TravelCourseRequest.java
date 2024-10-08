package org.backend.travelcourse.dto;

import java.util.List;
import org.backend.place.dto.PlaceRequest;
import org.backend.travelcourse.domain.TravelCourse;

public record TravelCourseRequest(
        String courseName,
        int days,
        List<PlaceRequest> places
) {

    public TravelCourse toEntity() {
        return new TravelCourse(
                courseName,
                days
        );
    }
}
