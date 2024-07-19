package org.backend.location.dto;

import org.backend.location.domain.Location;

public record LocationRequest(
        String name,
        Double latitude,
        Double longitude
) {

    public Location toEntity() {
        return new Location(
                name,
                latitude,
                longitude
        );
    }
}
