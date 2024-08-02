package org.backend.location.dto;

import org.backend.location.domain.Location;

public record LocationResponse(
        Long id,
        String name,
        String address,
        Double lattitude,
        Double longitude
) {

    public Location toEntity() {
        return new Location(
                id,
                name,
                address,
                lattitude,
                longitude
        );
    }
}
