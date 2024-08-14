package org.backend.place.dto;

import org.backend.place.domain.Place;
import org.backend.place.domain.PlaceType;

public record PlaceResponse(
        Long placeId,
        String contentid,
        String title,
        PlaceType contentTypeId,
        String addr1,
        Double mapx,
        Double mapy,
        String firstimage,
        String description
) {
    public static PlaceResponse toResponseDto(Place place) {
        return new PlaceResponse(
                place.getPlaceId(),
                place.getContentid(),
                place.getTitle(),
                place.getContentTypeId(),
                place.getAddr1(),
                place.getMapx(),
                place.getMapy(),
                place.getFirstimage(),
                place.getDescription()
        );
    }
}
