package org.backend.travelcourse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.backend.global.response.ApiResponse;
import org.backend.global.response.ResponseCode;
import org.backend.place.application.PlaceService;
import org.backend.place.domain.Place;
import org.backend.travelcourse.domain.TravelCourse;
import org.backend.travelcourse.dto.TravelCourseListResponse;
import org.backend.travelcourse.dto.TravelCourseRequest;
import org.backend.travelcourse.dto.TravelCourseResponse;
import org.backend.travelcourse.application.TravelCourseService;
import org.backend.travelcourse.exception.TravelCourseBadRequestException;
import org.backend.travelcoursedetail.application.TravelCourseDetailService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "TravelCourse Controller", description = "여행코스에 대한 API")
public class TravelCourseController {

    private final TravelCourseService travelCourseService;
    private final TravelCourseDetailService travelCourseDetailService;
    private final PlaceService placeService;

    public TravelCourseController(TravelCourseService travelCourseService,
                                  TravelCourseDetailService travelCourseDetailService,
                                  PlaceService placeService) {
        this.travelCourseService = travelCourseService;
        this.travelCourseDetailService = travelCourseDetailService;
        this.placeService = placeService;
    }

    @PostMapping("/travel-courses")
    @Operation(summary = "여행코스 생성", description = "여행 코스를 생성하기 위해 사용하는 API")
    public ApiResponse<Void> create(@RequestBody TravelCourseRequest travelCourseRequest) {
        if (travelCourseRequest.courseName() == null) {
            throw new TravelCourseBadRequestException(ResponseCode.BAD_REQUEST);
        }

        TravelCourse travelCourse = travelCourseService.save(travelCourseRequest);
        travelCourseRequest.places()
                .forEach(placeRequest -> {
                    List<Place> places = placeService.findOrSave(placeRequest.places());
                    travelCourseDetailService.saveTravelCourseDetails(travelCourse, places, placeRequest.day());
                });

        return ApiResponse.success(ResponseCode.COURSE_CREATED);
    }

    @GetMapping("/travel-courses/{id}")
    @Operation(summary = "단일 여행코스 정보 조회", description = "여행코스 고유 ID 값을 이용해 여행코스를 조회하기 위해 사용하는 API")
    public ApiResponse<TravelCourseResponse> myTravelCourse(@PathVariable Long id) {
        return ApiResponse.success(ResponseCode.COURSE_READ_SUCCESS, travelCourseService.findById(id));
    }

    @GetMapping("/travel-courses/members/{id}")
    @Operation(summary = "사용자의 여행코스 목록 조회", description = "사용자의 고유 ID를 이용해 저장한 여행코스 목록을 조회하기 위해 사용하는 API")
    public ApiResponse<List<TravelCourseListResponse>> myTravelCourseList(@PathVariable Long id) {
        return ApiResponse.success(ResponseCode.COURSE_MEMBER_READ_SUCCESS, travelCourseService.findByMemberId(id));
    }

    @GetMapping("/travel-courses/youtubers")
    @Operation(summary = "유튜버의 여행코스 목록 조회", description = "저장된 유튜버의 여행코스 목록을 조회하기 위해 사용하는 API")
    public ApiResponse<List<TravelCourseListResponse>> youtuberTravelCourseList() {
        return ApiResponse.success(ResponseCode.COURSE_YOUTUBER_READ_SUCCESS, travelCourseService.findYoutuberTravelCourse());
    }

    @DeleteMapping("/travel-courses/{id}")
    @Operation(summary = "여행코스 삭제", description = "여행 코스 고유 ID를 이용해 여행코스 정보를 삭제하기 위해 사용하는 API")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        travelCourseService.deleteById(id);
        return ApiResponse.success(ResponseCode.LOCATION_DELETE_SUCCESS);
    }
}
