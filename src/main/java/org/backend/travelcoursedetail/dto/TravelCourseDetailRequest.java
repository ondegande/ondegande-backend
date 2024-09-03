package org.backend.travelcoursedetail.dto;

import java.util.List;
import org.backend.place.domain.Place;
import org.backend.travelcoursedetail.domain.TravelCourseDetail;

public record TravelCourseDetailRequest(
        List<Place> places,
        int day,
        int orderInDay
) {

    public TravelCourseDetail toEntity() {
        return new TravelCourseDetail(
                day,
                orderInDay
        );
    }
}
