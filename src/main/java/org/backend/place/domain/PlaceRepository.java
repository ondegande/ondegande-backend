package org.backend.place.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("Select l "
            + "from Place l "
            + "where l.mapx = :mapx "
            + "And l.mapy = :mapy")
    Optional<Place> findByLatitudeAndLongitude(Double mapx, Double mapy);
}
