package org.backend.travelcoursedetail.domain;

import java.util.List;
import java.util.Optional;
import org.backend.travelcourse.domain.TravelCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelCourseDetailRepository extends JpaRepository<TravelCourseDetail, Long> {

    Optional<List<TravelCourseDetail>> findTravelCourseDetailsByTravelCourse(TravelCourse travelCourse);
}
