package org.backend.travelcourse.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.backend.member.domain.Member;
import org.backend.member.domain.Role;
import org.backend.place.domain.Place;
import org.backend.travelcourse.domain.CreatorType;
import org.backend.travelcourse.domain.TravelCourse;
import org.backend.travelcourse.domain.TravelCourseRepository;
import org.backend.travelcourse.dto.TravelCourseListResponse;
import org.backend.travelcourse.dto.TravelCourseRequest;
import org.backend.travelcourse.dto.TravelCourseResponse;
import org.backend.travelcourse.exception.TravelCouresNotFoundException;
import org.backend.travelcoursedetail.domain.TravelCourseDetail;
import org.backend.travelcoursedetail.domain.TravelCourseDetailRepository;
import org.backend.travelcoursedetail.excetion.TravelCourseDetailNotFoundException;
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

    @Mock
    private TravelCourseDetailRepository travelCourseDetailRepository;

    @InjectMocks
    private TravelCourseService travelCourseService;

    private TravelCourse travelCourse;
    private TravelCourseRequest travelCourseRequest;
    private Member member;
    private List<TravelCourseDetail> travelCourseDetails;

    @BeforeEach
    void setUp() {
        Place place1 = new Place(1L, "Place1", 37.7749, -122.4194);
        Place place2 = new Place(2L, "Place2", 12.7749, -122.4194);
        Place place3 = new Place(3L, "Place3", 16.7749, -122.4194);
        Place place4 = new Place(4L, "Place4", 37.7749, -122.4194);
        Place place5 = new Place(5L, "Place5", 12.7749, -122.4194);
        Place place6 = new Place(6L, "Place6", 16.7749, -122.4194);

        travelCourseRequest = new TravelCourseRequest(
                "나만의 코스",
                false,
                2,
                List.of()
        );

        member = new Member(
                1L,
                "test@test.com",
                null,
                null,
                Role.USER);

        travelCourse = new TravelCourse(
                1L,
                "나만의 코스",
                false,
                2,
                "테스트TV",
                CreatorType.YOUTUBER,
                null);

        travelCourseDetails = List.of(
                new TravelCourseDetail(travelCourse, place1, 1, 1),
                new TravelCourseDetail(travelCourse, place2, 1, 2),
                new TravelCourseDetail(travelCourse, place3, 1, 3),
                new TravelCourseDetail(travelCourse, place4, 2, 1),
                new TravelCourseDetail(travelCourse, place5, 2, 2),
                new TravelCourseDetail(travelCourse, place6, 2, 3)
        );
    }

    @Test
    @DisplayName("여행 코스 저장을 테스트합니다.")
    void testSave() {
        // when
        when(travelCourseRepository.save(any(TravelCourse.class))).thenReturn(travelCourse);
        TravelCourse response = travelCourseService.save(travelCourseRequest);

        // then
        assertNotNull(response);
        assertEquals("나만의 코스", response.getCourseName());
    }

    @Test
    @DisplayName("고유 아이디 값으로 여행코스 조회를 테스트합니다.")
    void testFindById() {
        // when
        when(travelCourseRepository.findById(1L)).thenReturn(Optional.of(travelCourse));
        when(travelCourseDetailRepository.findTravelCourseDetailsByTravelCourse(travelCourse))
                .thenReturn(Optional.of(travelCourseDetails));
        TravelCourseResponse response = travelCourseService.findById(1L);

        // then
        assertNotNull(response);
        assertEquals("나만의 코스", response.courseName());
        assertEquals(travelCourseDetails.get(0).getOrderInDay(), response.travelCourseDetailResponse().get(0).orderInDay());
    }

    @Test
    @DisplayName("고유 아이디로 여행코스 정보 조회 시 찾을 수 없는 예외 발생 테스트입니다.")
    void testTravelCourseNotFoundException() {
        // when
        when(travelCourseRepository.findById(1L)).thenThrow(TravelCouresNotFoundException.class);

        // then
        assertThrows(TravelCouresNotFoundException.class, () -> travelCourseService.findById(1L));
    }

    @Test
    @DisplayName("고유 아이디로 여행코스 정보 조회 시 여행코스 상세정보를 찾을 수 없는 예외 발생 테스트입니다.")
    void testTravelCourseDetailNotFoundException() {
        // when
        when(travelCourseRepository.findById(1L)).thenReturn(Optional.of(travelCourse));
        when(travelCourseDetailRepository.findTravelCourseDetailsByTravelCourse(travelCourse))
                .thenThrow(TravelCourseDetailNotFoundException.class);

        // then
        assertThrows(TravelCourseDetailNotFoundException.class, () -> travelCourseService.findById(1L));
    }

    @Test
    @DisplayName("회원의 고유 값으로 여행코스 리스트 조회를 테스트합니다.")
    void testFindByMemberId() {
        // given
        TravelCourse travelCourse1 = new TravelCourse(1L, "나만의 코스", false, 2, "테스트TV", CreatorType.YOUTUBER, member);
        TravelCourse travelCourse2 = new TravelCourse(2L, "나만의 코스2", false, 2, "테스트TV2", CreatorType.YOUTUBER, member);

        List<TravelCourse> courseList = List.of(travelCourse1, travelCourse2);

        // when
        when(travelCourseRepository.findByMemberId(member.getId())).thenReturn(Optional.of(courseList));
        List<TravelCourseListResponse> responses = travelCourseService.findByMemberId(member.getId());

        // then
        assertNotNull(responses);
        assertEquals("나만의 코스", responses.get(0).courseName());
        assertEquals("나만의 코스2", responses.get(1).courseName());
    }

    @Test
    @DisplayName("회원 정보로 여행코스 조회 시 찾을 수 없는 예외 처리 발생 테스트입니다.")
    void testFindByMemberIdException() {
        // when
        when(travelCourseRepository.findByMemberId(member.getId())).thenThrow(TravelCouresNotFoundException.class);

        // then
        assertThrows(TravelCouresNotFoundException.class, () -> travelCourseService.findByMemberId(member.getId()));
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
        assertThrows(TravelCouresNotFoundException.class, () -> travelCourseService.deleteById(1L));
    }

    @Test
    @DisplayName("유튜버 코스 조회를 테스트합니다.")
    void testFindYoutuberTravelCourse() {
        // when
        when(travelCourseRepository.findTravelCoursesByCreatorType(CreatorType.YOUTUBER))
                .thenReturn(Optional.of(List.of(travelCourse)));

        List<TravelCourseListResponse> responses = travelCourseService.findYoutuberTravelCourse();

        // then
        assertNotNull(responses);
        assertEquals(travelCourse.getCourseName(), responses.get(0).courseName());
    }
}