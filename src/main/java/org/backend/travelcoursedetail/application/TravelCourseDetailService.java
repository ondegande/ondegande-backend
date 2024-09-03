package org.backend.travelcoursedetail.application;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.backend.place.domain.Place;
import org.backend.travelcourse.domain.TravelCourse;
import org.backend.travelcoursedetail.domain.TravelCourseDetail;
import org.backend.travelcoursedetail.domain.TravelCourseDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class TravelCourseDetailService {

    private final TravelCourseDetailRepository travelCourseDetailRepository;

    public TravelCourseDetailService(TravelCourseDetailRepository travelCourseDetailRepository) {
        this.travelCourseDetailRepository = travelCourseDetailRepository;
    }

    @Transactional
    public void saveTravelCourseDetails(TravelCourse travelCourse, List<Place> places, int day) {
        AtomicInteger orderInDay = new AtomicInteger(1);

        places.stream()
                .map(place -> new TravelCourseDetail(travelCourse, place, day, orderInDay.getAndIncrement()))
                .forEach(travelCourseDetailRepository::save);
    }
}
