package org.backend.travelcourse.dto;

import org.backend.travelcourse.domain.CreatorType;
import org.backend.travelcourse.domain.TravelCourse;

public record TravelCourseListResponse(
        Long id,
        String courseName,
        int days,
        String creatorName,
        CreatorType creatorType,
        String youtubeUrl,
        String youtubeImageUrl,
        Long viewCount
) {
    public static TravelCourseListResponse toResponseListDto(TravelCourse travelCourse) {
        return new TravelCourseListResponse(
                travelCourse.getTravelCourseId(),
                travelCourse.getCourseName(),
                travelCourse.getDays(),
                travelCourse.getCreatorName(),
                travelCourse.getCreatorType(),
                travelCourse.getYoutubeUrl(),
                travelCourse.getYoutubeImageUrl(),
                travelCourse.getViewCount()
        );
    }
}
