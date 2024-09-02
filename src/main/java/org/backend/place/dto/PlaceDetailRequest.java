package org.backend.place.dto;

import org.backend.place.domain.Place;

public record PlaceDetailRequest(
        String placeName,
        Double latitude,
        Double longitude
) {

    public Place toEntity() {
        return new Place(
                placeName,
                latitude,
                longitude
        );
    }
}
