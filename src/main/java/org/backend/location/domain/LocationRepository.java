package org.backend.location.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("Select l "
            + "from Location l "
            + "where l.latitude = :latitude "
            + "And l.longitude = :longitude")
    Optional<Location> findByLatitudeAndLongitude(Double latitude, Double longitude);
}
