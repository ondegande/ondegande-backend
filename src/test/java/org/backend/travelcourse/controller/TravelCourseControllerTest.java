package org.backend.travelcourse.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.backend.place.application.PlaceService;
import org.backend.place.domain.Place;
import org.backend.place.dto.PlaceDetailRequest;
import org.backend.place.dto.PlaceRequest;
import org.backend.travelcourse.application.TravelCourseService;
import org.backend.travelcourse.domain.CreatorType;
import org.backend.travelcourse.domain.TravelCourse;
import org.backend.travelcourse.dto.TravelCourseListResponse;
import org.backend.travelcourse.dto.TravelCourseRequest;
import org.backend.travelcourse.dto.TravelCourseResponse;
import org.backend.travelcoursedetail.application.TravelCourseDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TravelCourseController.class)
@ActiveProfiles("local")
public class TravelCourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TravelCourseService travelCourseService;

    @MockBean
    private TravelCourseDetailService travelCourseDetailService;

    @MockBean
    private PlaceService placeService;

    @Autowired
    private ObjectMapper mapper;

    private TravelCourseRequest validRequest;
    private TravelCourseRequest invalidRequest;
    private TravelCourse travelCourse;
    private Long travelCourseId;
    private List<PlaceRequest> placeRequests;
    private List<Place> places1;

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

        places1 = List.of(place1, place2, place3);

        placeRequests = List.of(placeRequest1);

        validRequest = new TravelCourseRequest(
                "나만의 코스",
                false,
                2,
                placeRequests
        );

        invalidRequest = new TravelCourseRequest(
                null,
                false,
                2,
                null
        );

        travelCourse = new TravelCourse(
                1L,
                "나만의 코스",
                false,
                2,
                "테스트TV",
                CreatorType.YOUTUBER,
                null,
                null
        );

        travelCourseId = 1L;
    }

    @Test
    @DisplayName("여행코스 생성 API를 테스트합니다.")
    @WithMockUser(username = "test", roles = "USER")
    void testCreate() throws Exception {
        // when
        when(travelCourseService.save(validRequest)).thenReturn(travelCourse);
        when(placeService.findOrSave(placeRequests.get(0).places())).thenReturn(places1);

        // then
        mockMvc.perform(post("/api/travel-courses")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(validRequest)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("여행코스 생성 시 잘못된 요청 예외 발생을 테스트합니다.")
    @WithMockUser(username = "test User", roles = "USER")
    void testCreateBacRequestException() throws Exception {
        // then
        mockMvc.perform(post("/api/travel-courses")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("단일 여행코스 정보 조회 API를 테스트합니다.")
    @WithMockUser(username = "test User", roles = "USER")
    void testMyTravelCourse() throws Exception {
        // when
        when(travelCourseService.findById(travelCourseId)).thenReturn(TravelCourseResponse.toResponseDto(travelCourse, List.of()));

        // then
        mockMvc.perform(get("/api/travel-courses/{id}", travelCourseId)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.data.courseName").value("나만의 코스"));
    }

    @Test
    @DisplayName("여행코스 삭제 API를 테스트합니다.")
    @WithMockUser(username = "testUser", roles = "USER")
    void testDelete() throws Exception {
        // when
        doNothing().when(travelCourseService).deleteById(travelCourseId);

        // then
        mockMvc.perform(delete("/api/travel-courses/{id}", travelCourseId)
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("유튜버 여행코스 조회 API를 테스트합니다.")
    @WithMockUser(username = "testUser", roles = "USER")
    void testYoutuberTravelCourseList() throws Exception {
        // given
        TravelCourse travelCourse1 = new TravelCourse(
                1L,
                "나만의 코스",
                false,
                2,
                "테스트TV",
                CreatorType.YOUTUBER,
                null,
                null
        );

        TravelCourse travelCourse2 = new TravelCourse(
                2L,
                "나만의 코스2",
                false,
                2,
                "테스트TV2",
                CreatorType.YOUTUBER,
                null,
                null
        );

        List<TravelCourseListResponse> courseList = List.of(TravelCourseListResponse.toResponseListDto(travelCourse1), TravelCourseListResponse.toResponseListDto(travelCourse2));

        // when
        when(travelCourseService.findYoutuberTravelCourse()).thenReturn(courseList);

        // then
        mockMvc.perform(get("/api/travel-courses/youtubers")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType("application/json"))
                .andExpect(jsonPath("$.body.data[0].creatorType").value("YOUTUBER"))
                .andExpect(jsonPath("$.body.data[0].creatorName").value("테스트TV"));

    }
}