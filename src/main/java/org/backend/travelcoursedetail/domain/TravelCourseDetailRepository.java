package org.backend.travelcoursedetail.domain;

import java.util.List;
import java.util.Optional;
import org.backend.travelcourse.domain.TravelCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TravelCourseDetailRepository extends JpaRepository<TravelCourseDetail, Long> {

    @Query(value = "SELECT * "
            + "FROM travel_course_detail "
            + "WHERE travel_course_id = :travelCourseId "
            + "ORDER BY course_day ASC, order_in_day ASC",nativeQuery = true)
    Optional<List<TravelCourseDetail>> findTravelCourseDetailsByTravelCourseId(@Param("travelCourseId") Long travelCourseId);
}
