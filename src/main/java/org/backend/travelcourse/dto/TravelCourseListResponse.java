package org.backend.travelcourse.dto;

import org.backend.travelcourse.domain.CreatorType;
import org.backend.travelcourse.domain.TravelCourse;

public record TravelCourseListResponse(
        Long id,
        String courseName,
        boolean isShared,
        int days,
        String creatorName,
        CreatorType creatorType,
        String youtubeUrl,
        String youtubeImageUrl
) {
    public static TravelCourseListResponse toResponseListDto(TravelCourse travelCourse) {
        return new TravelCourseListResponse(
                travelCourse.getTravelCourseId(),
                travelCourse.getCourseName(),
                travelCourse.isShared(),
                travelCourse.getDays(),
                travelCourse.getCreatorName(),
                travelCourse.getCreatorType(),
                travelCourse.getYoutubeUrl(),
                travelCourse.getYoutubeImageUrl()
        );
    }
}
