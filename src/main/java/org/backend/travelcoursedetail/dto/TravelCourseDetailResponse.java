package org.backend.travelcoursedetail.dto;

import org.backend.place.dto.PlaceResponse;
import org.backend.travelcoursedetail.domain.TravelCourseDetail;

public record TravelCourseDetailResponse(
        PlaceResponse placeResponse,
        int day,
        int orderInDay
) {
    public static TravelCourseDetailResponse toResponseDto(TravelCourseDetail travelCourseDetail) {
        return new TravelCourseDetailResponse(
                PlaceResponse.toResponseDto(travelCourseDetail.getPlace()),
                travelCourseDetail.getCourseDay(),
                travelCourseDetail.getOrderInDay()
        );
    }
}
