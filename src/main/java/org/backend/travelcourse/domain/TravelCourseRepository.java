package org.backend.travelcourse.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelCourseRepository extends JpaRepository<TravelCourse, Long> {

    Optional<List<TravelCourse>> findByMemberId(Long id);

    Optional<List<TravelCourse>> findTravelCoursesByCreatorType(CreatorType creatorType);
}
