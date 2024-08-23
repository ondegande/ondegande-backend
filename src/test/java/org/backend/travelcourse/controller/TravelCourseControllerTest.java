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
import org.backend.member.domain.Member;
import org.backend.member.domain.Role;
import org.backend.travelcourse.application.TravelCourseService;
import org.backend.travelcourse.domain.TravelCourse;
import org.backend.travelcourse.dto.TravelCourseRequest;
import org.backend.travelcourse.dto.TravelCourseResponse;
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

    @Autowired
    private ObjectMapper mapper;

    private TravelCourseRequest validRequest;
    private TravelCourseRequest invalidRequest;
    private TravelCourse travelCourse;
    private Member member;
    private Long travelCourseId;
    private Long memberId;

    @BeforeEach
    void setUp() {
        validRequest = new TravelCourseRequest(
                "나만의 코스",
                false
        );

        invalidRequest = new TravelCourseRequest(
                null,
                false
        );

        member = new Member(
                1L,
                "test@test.com",
                null,
                null,
                Role.USER
        );

        travelCourse = new TravelCourse(
                "나만의 코스",
                false,
                member
        );

        travelCourseId = 1L;
        memberId = 1L;
    }

    @Test
    @DisplayName("여행코스 생성 API를 테스트합니다.")
    @WithMockUser(username = "test", roles = "USER")
    void testCreate() throws Exception {
        // when
        when(travelCourseService.save(validRequest)).thenReturn(TravelCourseResponse.toResponseDto(travelCourse));

        // then
        mockMvc.perform(post("/api/travel-courses")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(validRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.data.name").value("나만의 코스"));
    }

    @Test
    @DisplayName("여행코스 생성 시 멤버 권한 예외 발생 테스트를 진행합니다.")
    @WithMockUser(username = "test", roles = "GUEST")
    void testCreateAuthorizationException() throws Exception {
        // when
        when(travelCourseService.save(validRequest)).thenThrow(AccessDeniedException.class);

        // then
        mockMvc.perform(post("/api/travel-courses")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(validRequest)))
                .andExpect(status().isForbidden());
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
        when(travelCourseService.findById(travelCourseId)).thenReturn(TravelCourseResponse.toResponseDto(travelCourse));

        // then
        mockMvc.perform(get("/api/travel-courses/{id}", travelCourseId)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.data.name").value("나만의 코스"));
    }

    @Test
    @DisplayName("사용자의 여행코스 목록 조회")
    @WithMockUser(username = "test User", roles = "USER")
    void testMyTravelCourseList() throws Exception {
        // given
        TravelCourse travelCourse1 = new TravelCourse(
                1L,
                "나만의 코스",
                false,
                member
        );

        TravelCourse travelCourse2 = new TravelCourse(
                2L,
                "나만의 코스2",
                false,
                member
        );

        List<TravelCourseResponse> courseList = List.of(TravelCourseResponse.toResponseDto(travelCourse1), TravelCourseResponse.toResponseDto(travelCourse2));

        // when
        when(travelCourseService.findByMemberId(memberId)).thenReturn(courseList);

        // then
        mockMvc.perform(get("/api/travel-courses/members/{id}", memberId)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.data[0].name").value("나만의 코스"));
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
}
