package org.backend.place.dto;

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
}
