package org.backend.travelcourse.dto;

import java.util.List;
import org.backend.travelcourse.domain.CreatorType;
import org.backend.travelcourse.domain.TravelCourse;
import org.backend.travelcoursedetail.dto.TravelCourseDetailResponse;

public record TravelCourseResponse(
        Long id,
        String courseName,
        int days,
        String creatorName,
        CreatorType creatorType,
        String youtubeUrl,
        String youtubeImageUrl,
        List<TravelCourseDetailResponse> travelCourseDetailResponse,
        Long viewCount
) {
    public static TravelCourseResponse toResponseDto(TravelCourse travelCourse, List<TravelCourseDetailResponse> travelCourseDetailResponses) {
        return new TravelCourseResponse(
                travelCourse.getTravelCourseId(),
                travelCourse.getCourseName(),
                travelCourse.getDays(),
                travelCourse.getCreatorName(),
                travelCourse.getCreatorType(),
                travelCourse.getYoutubeUrl(),
                travelCourse.getYoutubeImageUrl(),
                travelCourseDetailResponses,
                travelCourse.getViewCount()
        );
    }
}
