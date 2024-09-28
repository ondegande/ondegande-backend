package org.backend.place.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query(value = "SELECT * "
            + "FROM places l "
            + "WHERE l.latitude = :latitude "
            + "And l.longitude = :longitude", nativeQuery = true)
    Optional<Place> findByLatitudeAndLongitude(@Param("latitude") Double latitude, @Param("longitude") Double longitude);
}
