package org.backend.place.dto;

import java.util.List;

public record PlaceRequest(
        int day,
        List<PlaceDetailRequest> places
) {
}
