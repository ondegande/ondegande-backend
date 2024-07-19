package org.backend.travelcourse.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelCourseRepository extends JpaRepository<TravelCourse, Long> {

    List<TravelCourse> findByMemberId(Long id);
}
