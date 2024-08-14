package org.backend.place.dto;

import org.backend.place.domain.Place;
import org.backend.place.domain.PlaceType;

public record PlaceRequest(
        String contentid,
        String title,
        PlaceType contentTypeId,
        String addr1,
        Double mapx,
        Double mapy,
        String firstimage,
        String description
) {

    public Place toEntity() {
        return new Place(
                contentid,
                title,
                contentTypeId,
                addr1,
                mapx,
                mapy,
                firstimage,
                description
        );
    }
}
