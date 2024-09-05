package org.backend.place.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query(value = "Select * "
            + "from places l "
            + "where l.latitude = :latitude "
            + "And l.longitude = :longitude", nativeQuery = true)
    Optional<Place> findByLatitudeAndLongitude(Double latitude, Double longitude);
}
