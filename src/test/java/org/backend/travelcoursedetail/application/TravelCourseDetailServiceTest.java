package org.backend.travelcoursedetail.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.List;
import org.backend.place.domain.Place;
import org.backend.travelcourse.domain.CreatorType;
import org.backend.travelcourse.domain.TravelCourse;
import org.backend.travelcoursedetail.domain.TravelCourseDetail;
import org.backend.travelcoursedetail.domain.TravelCourseDetailRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("local")
public class TravelCourseDetailServiceTest {

    @Mock
    private TravelCourseDetailRepository travelCourseDetailRepository;

    @InjectMocks
    private TravelCourseDetailService travelCourseDetailService;

    @Test
    @DisplayName("여행 코스 상세 정보 저장 테스트")
    void testSaveTravelCourseDetails() {
        // given
        TravelCourse travelCourse = new TravelCourse(
                1L,
                "나만의 코스",
                2,
                "테스트TV",
                CreatorType.YOUTUBER,
                null,
                null,
                10000L
        );

        List<Place> places = List.of(
                new Place(1L, "Place1", 37.7749, -122.4194),
                new Place(2L, "Place2", 12.7749, -122.4194),
                new Place(3L, "Place3", 16.7749, -122.4194),
                new Place(4L, "Place4", 37.7749, -122.4194),
                new Place(5L, "Place5", 12.7749, -122.4194),
                new Place(6L, "Place6", 16.7749, -122.4194)
        );

        int day = 1;

        // when
        travelCourseDetailService.saveTravelCourseDetails(travelCourse, places, day);

        // then
        // 모든 Place 객체에 대해 TravelCourseDetail이 저장되었는지 확인
        verify(travelCourseDetailRepository, times(places.size())).save(any(TravelCourseDetail.class));
    }
}