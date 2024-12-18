package org.backend.travelcourse.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.backend.global.exception.NotFoundException;
import org.backend.global.response.ResponseCode;
import org.backend.place.domain.Place;
import org.backend.place.dto.PlaceDetailRequest;
import org.backend.place.dto.PlaceRequest;
import org.backend.travelcourse.application.TravelCourseService;
import org.backend.travelcourse.domain.CreatorType;
import org.backend.travelcourse.domain.TravelCourse;
import org.backend.travelcourse.dto.TravelCourseListResponse;
import org.backend.travelcourse.dto.TravelCourseResponse;
import org.backend.travelcourse.dto.TravelCourses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TravelCourseController.class)
@ActiveProfiles("local")
public class TravelCourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TravelCourseService travelCourseService;

    @Autowired
    private ObjectMapper mapper;

    private TravelCourse travelCourse;
    private Long travelCourseId;

    @BeforeEach
    void setUp() {
        Place place1 = new Place(1L, "Place1", 37.7749, -122.4194);
        Place place2 = new Place(2L, "Place2", 12.7749, -122.4194);
        Place place3 = new Place(3L, "Place3", 16.7749, -122.4194);

        PlaceRequest placeRequest1 = new PlaceRequest(
                1,
                List.of(
                        new PlaceDetailRequest("Place1", 37.7749, -122.4194),
                        new PlaceDetailRequest("Place2", 12.7749, -122.4194),
                        new PlaceDetailRequest("Place3", 16.7749, -122.4194))
        );

        travelCourse = new TravelCourse(
                1L,
                "나만의 코스",
                2,
                "테스트TV",
                CreatorType.YOUTUBER,
                null,
                null,
                100000L
        );

        travelCourseId = 1L;
    }

    @Test
    @DisplayName("단일 여행코스 정보 조회 API를 테스트합니다.")
    void testMyTravelCourse() throws Exception {
        // when
        when(travelCourseService.findById(travelCourseId)).thenReturn(TravelCourseResponse.toResponseDto(travelCourse, List.of()));

        // then
        mockMvc.perform(get("/api/travel-courses/{id}", travelCourseId)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.data.courseName").value("나만의 코스"))
                .andDo(print());
    }

    @Test
    @DisplayName("단일 여행코스 정보 조회 시 발생하는 예외처리를 테스트합니다.")
    void testTravelCourseNotFoundException() throws Exception {
        // when
        when(travelCourseService.findById(travelCourseId))
                .thenThrow(new NotFoundException(ResponseCode.COURSE_NOT_FOUND));

        // then
        mockMvc.perform(get("/api/travel-courses/{id}", travelCourseId)
                        .contentType("application/json"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("유튜버 여행코스 리스트 조회 API를 테스트합니다.")
    void testYoutuberTravelCourseList() throws Exception {
        // given
        TravelCourse travelCourse1 = new TravelCourse(
                1L,
                "나만의 코스",
                2,
                "테스트TV",
                CreatorType.YOUTUBER,
                null,
                null,
                10000L
        );

        TravelCourse travelCourse2 = new TravelCourse(
                2L,
                "나만의 코스2",
                2,
                "테스트TV2",
                CreatorType.YOUTUBER,
                null,
                null,
                10000L
        );

        List<TravelCourseListResponse> courseList = List.of(TravelCourseListResponse.toResponseListDto(travelCourse1), TravelCourseListResponse.toResponseListDto(travelCourse2));
        TravelCourses travelCourses = new TravelCourses(courseList);

        // when
        when(travelCourseService.findYoutuberTravelCourseByCreatorType()).thenReturn(travelCourses);

        // then
        mockMvc.perform(get("/api/travel-courses/youtubers")
                .contentType("application/json"))
                .andExpect(jsonPath("$.body.data.travelCourses[0].creatorType").value("YOUTUBER"))
                .andExpect(jsonPath("$.body.data.travelCourses[0].creatorName").value("테스트TV"))
                .andDo(print());
    }

    @Test
    @DisplayName("유튜버 여행코스 조회수 필터 조회 테스트입니다.")
    void testYotubeTravelCourseListByViewCount() throws Exception {
        // given
        Sort sort = Sort.by(Sort.Direction.fromString("DESC"), "viewCount");

        TravelCourse travelCourse1 = new TravelCourse(
                1L,
                "나만의 코스",
                2,
                "테스트TV",
                CreatorType.YOUTUBER,
                null,
                null,
                10000L
        );

        TravelCourse travelCourse2 = new TravelCourse(
                2L,
                "나만의 코스2",
                2,
                "테스트TV2",
                CreatorType.YOUTUBER,
                null,
                null,
                12000L
        );

        List<TravelCourseListResponse> courseList = List.of(TravelCourseListResponse.toResponseListDto(travelCourse1), TravelCourseListResponse.toResponseListDto(travelCourse2));

        // when
        when(travelCourseService.findYoutuberTravelCourseByViewCount(sort)).thenReturn(courseList);

        // then
        mockMvc.perform(get("/api/travel-courses/youtubers/reviews?sortDirection=DESC")
                        .contentType("application/json"))
                .andExpect(jsonPath("$.body.data[0].creatorType").value("YOUTUBER"))
                .andExpect(jsonPath("$.body.data[0].creatorName").value("테스트TV"))
                .andExpect(jsonPath("$.body.data[0].viewCount").value(travelCourse1.getViewCount()))
                .andDo(print());
    }

    @Test
    @DisplayName("유튜버 여행코스 정보 랜덤 조회 API를 테스트합니다.")
    void testRandomYoutuberTravelCourse() throws Exception {
        // when
        when(travelCourseService.findRandomYoutuberTravelCourseByCreatorType()).thenReturn(TravelCourseResponse.toResponseDto(travelCourse, List.of()));

        // then
        mockMvc.perform(get("/api/travel-courses/youtubers/random")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.data.courseName").value("나만의 코스"))
                .andDo(print());
    }
}