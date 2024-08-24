package org.backend.place.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("Select l "
            + "from Place l "
            + "where l.latitude = :latitude "
            + "And l.longitude = :longitude")
    Optional<Place> findByLatitudeAndLongitude(Double latitude, Double longitude);
}
