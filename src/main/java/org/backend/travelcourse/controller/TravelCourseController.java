package org.backend.travelcourse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.backend.global.exception.BadRequestException;
import org.backend.global.response.ApiResponse;
import org.backend.global.response.ResponseCode;
import org.backend.place.application.PlaceService;
import org.backend.place.domain.Place;
import org.backend.travelcourse.domain.TravelCourse;
import org.backend.travelcourse.dto.TravelCourseListResponse;
import org.backend.travelcourse.dto.TravelCourseRequest;
import org.backend.travelcourse.dto.TravelCourseResponse;
import org.backend.travelcourse.application.TravelCourseService;
import org.backend.travelcourse.dto.TravelCourses;
import org.backend.travelcoursedetail.application.TravelCourseDetailService;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "TravelCourse Controller", description = "여행코스에 대한 API")
public class TravelCourseController {

    private final TravelCourseService travelCourseService;

    public TravelCourseController(TravelCourseService travelCourseService) {
        this.travelCourseService = travelCourseService;
    }

    @GetMapping("/travel-courses/{id}")
    @Operation(summary = "여행코스 상세 정보 조회", description = "여행코스 고유 ID 값을 이용해 여행코스 상세 정보를 조회하기 위해 사용하는 API")
    public ApiResponse<TravelCourseResponse> getMyTravelCourse(@PathVariable Long id) {
        return ApiResponse.success(ResponseCode.COURSE_READ_SUCCESS, travelCourseService.findById(id));
    }

    @GetMapping("/travel-courses/youtubers")
    @Operation(summary = "유튜버의 여행코스 목록 조회", description = "저장된 유튜버의 여행코스 목록을 조회하기 위해 사용하는 API")
    public ApiResponse<TravelCourses> youtuberTravelCourseList() {
        return ApiResponse.success(ResponseCode.COURSE_YOUTUBER_READ_SUCCESS, travelCourseService.findYoutuberTravelCourseByCreatorType());
    }

    @GetMapping("/travel-courses/youtubers/random")
    @Operation(summary = "유튜버의 여행코스 랜덤 조회", description = "저장된 유튜버의 여행코스 중 랜덤으로 1개를 조회하기 위해 사용하는 API")
    public ApiResponse<TravelCourseResponse> randomYoutuberTravelCourse() {
        return ApiResponse.success(ResponseCode.COURSE_YOUTUBER_READ_SUCCESS, travelCourseService.findRandomYoutuberTravelCourseByCreatorType());
    }

    @GetMapping("/travel-courses/youtubers/reviews")
    @Operation(summary = "유튜버의 여행코스 조회수 필터 조회", description = "유튜버의 여행코스 중 조회수가 높은 순서대로 조회하기 위해 사용하는 API")
    public ApiResponse<List<TravelCourseListResponse>> youtuberTravelCourseListByViewCount(@RequestParam String sortDirection) {
        return ApiResponse.success(ResponseCode.COURSE_YOUTUBER_READ_SUCCESS,
                travelCourseService.findYoutuberTravelCourseByViewCount(Sort.by(Sort.Direction.fromString(sortDirection), "viewCount")));
    }
}
