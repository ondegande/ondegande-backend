package org.backend.place.dto;

import org.backend.place.domain.Place;

public record PlaceResponse(
        Long placeId,
        String placeName,
        Double latitude,
        Double longitude
) {
    public static PlaceResponse toResponseDto(Place place) {
        return new PlaceResponse(
                place.getPlaceId(),
                place.getPlaceName(),
                place.getLatitude(),
                place.getLongitude()
        );
    }
}
