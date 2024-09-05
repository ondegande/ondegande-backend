package org.backend.travelcourse.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TravelCourseRepository extends JpaRepository<TravelCourse, Long> {

    Optional<List<TravelCourse>> findTravelCoursesByCreatorType(CreatorType creatorType);

    @Query(value = "SELECT *"
            + " FROM travel_course t"
            + " WHERE t.creator_type = :creatorType"
            + " ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<TravelCourse> findRandomTravelCourseByCreatorType(String creatorType);
}
