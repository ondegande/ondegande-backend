package org.backend.place.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.backend.place.application.PlaceService;
import org.backend.place.domain.Place;
import org.backend.place.dto.PlaceRequest;
import org.backend.place.dto.PlaceResponse;
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

@WebMvcTest(PlaceController.class)
@ActiveProfiles("local")
public class PlaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlaceService placeService;

    @Autowired
    private ObjectMapper mapper;

    private PlaceRequest validRequest;
    private PlaceRequest invalidRequest;
    private Place place;
    private Long placeId;

    @BeforeEach
    void setUp() {
        validRequest = new PlaceRequest(
                "장소1",
                10.123,
                10.135);

        invalidRequest = new PlaceRequest(
                "장소2",
                null,
                10.135);

        place = new Place(
                1L,
                "장소1",
                10.123,
                10.135);

        placeId = 1L;
    }

    @Test
    @DisplayName("PlaceController create() 메서드 테스트를 진행합니다.")
    @WithMockUser(username = "testUser", roles = "USER")
    void testCreate() throws Exception {
        // when
        when(placeService.save(validRequest)).thenReturn(PlaceResponse.toResponseDto(place));

        // then
        mockMvc.perform(post("/api/places")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(validRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.data.placeName").value("장소1"));
    }

    @Test
    @DisplayName("PlaceController create() 멤버 권한 예외 발생 테스트를 진행합니다.")
    @WithMockUser(username = "test User", roles = "GUEST")
    void testCreateAuthorizationException() throws Exception {
        // when
        when(placeService.save(validRequest)).thenThrow(AccessDeniedException.class);

        // then
        mockMvc.perform(post("/api/places")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(validRequest)))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("PlaceController create() 잘못된 요청 예외 발생 테스트를 진행합니다.")
    @WithMockUser(username = "test User", roles = "USER")
    void testCreateBadRequestException() throws Exception {
        // then
        mockMvc.perform(post("/api/places")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PlaceController location() 메서드인 장소 정보 조회 테스트를 진행합니다.")
    @WithMockUser(username = "test User", roles = "USER")
    void testLocation() throws Exception {
        // when
        when(placeService.findById(placeId)).thenReturn(PlaceResponse.toResponseDto(place));

        // then
        mockMvc.perform(get("/api/places/{id}", placeId)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.data.placeName").value("장소1"));
    }

    @Test
    @DisplayName("PlaceController delete() 메서드인 장소 정보 삭제 테스트를 진행합니다.")
    @WithMockUser(username = "test User", roles = "USER")
    void testDeleteLocation() throws Exception{
        // when
        doNothing().when(placeService).deleteById(placeId);

        // then
        mockMvc.perform(delete("/api/places/{id}", placeId)
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isOk());
    }
}
