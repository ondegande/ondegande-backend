package org.backend.travelcourse.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.backend.member.domain.Member;
import org.backend.member.domain.Role;
import org.backend.travelcourse.domain.Accommodation;
import org.backend.travelcourse.domain.Concept;
import org.backend.travelcourse.domain.Schedule;
import org.backend.travelcourse.domain.TravelCourse;
import org.backend.travelcourse.domain.TravelCourseRepository;
import org.backend.travelcourse.dto.TravelCourseRequest;
import org.backend.travelcourse.dto.TravelCourseResponse;
import org.backend.travelcourse.exception.TravelCouresNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("local")
public class TravelCourseServiceTest {

    @Mock
    private TravelCourseRepository travelCourseRepository;

    @InjectMocks
    private TravelCourseService travelCourseService;

    private TravelCourse travelCourse;
    private TravelCourseRequest travelCourseRequest;
    private Member member;

    @BeforeEach
    void setUp() {
        travelCourseRequest = new TravelCourseRequest(
                Schedule.ONE_NIGHTS,
                Concept.ACTIVITY_LEISURE,
                Accommodation.CONDOMINIUM,
                false
        );

        travelCourse = new TravelCourse(
                1L,
                Schedule.ONE_NIGHTS,
                Concept.ACTIVITY_LEISURE,
                Accommodation.CONDOMINIUM,
                false,
                null
        );

        member = new Member(
                1L,
                "test@test.com",
                null,
                null,
                Role.USER
        );
    }

    @Test
    @DisplayName("여행 코스 저장을 테스트합니다.")
    void testSave() {
        // when
        when(travelCourseRepository.save(any(TravelCourse.class))).thenReturn(travelCourse);
        TravelCourseResponse response = travelCourseService.save(travelCourseRequest);

        // then
        assertNotNull(response);
        assertEquals(response.concept(), Concept.ACTIVITY_LEISURE);
    }

    @Test
    @DisplayName("고유 아이디 값으로 여행코스 조회를 테스트합니다.")
    void testFindById() {
        // when
        when(travelCourseRepository.findById(1L)).thenReturn(Optional.of(travelCourse));
        TravelCourseResponse response = travelCourseService.findById(1L);

        // then
        assertNotNull(response);
        assertEquals(response.accommodation(), Accommodation.CONDOMINIUM);
    }

    @Test
    @DisplayName("고유 아이디로 여행코스 정보 조회 시 찾을 수 없는 예외 발생 테스트 입니다.")
    void testTravelCourseNotFundException() {
        // when
        when(travelCourseRepository.findById(1L)).thenThrow(TravelCouresNotFoundException.class);

        // then
        assertThrows(TravelCouresNotFoundException.class,
                () -> travelCourseService.findById(1L));
    }

    @Test
    @DisplayName("회원의 고유 값으로 여행코스 리스트 조회를 테스트합니다.")
    void testFindByMemberId() {
        // given
        TravelCourse travelCourse1 = new TravelCourse(
                1L,
                Schedule.ONE_NIGHTS,
                Concept.ACTIVITY_LEISURE,
                Accommodation.CONDOMINIUM,
                false,
                member
        );

        TravelCourse travelCourse2 = new TravelCourse(
                2L,
                Schedule.ONE_NIGHTS,
                Concept.ACTIVITY_LEISURE,
                Accommodation.CONDOMINIUM,
                false,
                member
        );

        List<TravelCourse> courseList = List.of(travelCourse1, travelCourse2);

        // when
        when(travelCourseRepository.findByMemberId(member.getId())).thenReturn(Optional.of(courseList));
        List<TravelCourseResponse> responses = travelCourseService.findByMemberId(member.getId());

        // then
        assertNotNull(responses);
        assertEquals(responses.get(0).accommodation(), Accommodation.CONDOMINIUM);
        assertEquals(responses.get(1).accommodation(), Accommodation.CONDOMINIUM);
    }

    @Test
    @DisplayName("회원 정보로 여행코스 조회 시 찾을 수 없는 예외 처리 발생 테스트입니다.")
    void testFindByMemberIdException() {
        // when
        when(travelCourseRepository.findByMemberId(member.getId())).thenThrow(TravelCouresNotFoundException.class);

        // then
        assertThrows(TravelCouresNotFoundException.class,
                () -> travelCourseService.findByMemberId(member.getId()));
    }

    @Test
    @DisplayName("고유 아이디로 여행코스 삭제를 테스트합니다.")
    void testDeleteById() {
        // when
        when(travelCourseRepository.findById(1L)).thenReturn(Optional.of(travelCourse));
        travelCourseService.deleteById(1L);

        // then
        verify(travelCourseRepository).deleteById(1L);
    }

    @Test
    @DisplayName("고유 아이디로 여행코스 삭제 시 예외 발생 테스트합니다.")
    void testDeleteByIdException() {
        // when
        when(travelCourseRepository.findById(1L)).thenReturn(Optional.empty());

        // then
        assertThrows(TravelCouresNotFoundException.class,
                () -> travelCourseService.deleteById(1L));
    }
}
