package org.backend.travelcourse.dto;

import java.util.List;
import org.backend.travelcourse.domain.CreatorType;
import org.backend.travelcourse.domain.TravelCourse;
import org.backend.travelcoursedetail.dto.TravelCourseDetailResponse;

public record TravelCourseResponse(
        Long id,
        String courseName,
        boolean isShared,
        int days,
        String creatorName,
        CreatorType creatorType,
        List<TravelCourseDetailResponse> travelCourseDetailResponse
) {
    public static TravelCourseResponse toResponseDto(TravelCourse travelCourse, List<TravelCourseDetailResponse> travelCourseDetailResponses) {
        return new TravelCourseResponse(
                travelCourse.getTravelCourseId(),
                travelCourse.getCourseName(),
                travelCourse.isShared(),
                travelCourse.getDays(),
                travelCourse.getCreatorName(),
                travelCourse.getCreatorType(),
                travelCourseDetailResponses
        );
    }
}
