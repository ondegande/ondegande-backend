package org.backend.place.application;

import org.backend.global.response.ResponseCode;
import org.backend.place.domain.Place;
import org.backend.place.dto.PlaceRequest;
import org.backend.place.dto.PlaceResponse;
import org.backend.place.domain.PlaceRepository;
import org.backend.place.exception.PlaceAlreadyExistException;
import org.backend.place.exception.PlaceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Transactional
    public PlaceResponse save(PlaceRequest request) {
//        validateLocation(request);

        Place place = placeRepository.save(request.toEntity());

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

    private void validateLocation(PlaceRequest request) {
        placeRepository.findByLatitudeAndLongitude(request.mapx(), request.mapy())
                .ifPresent(it -> {
                    throw new PlaceAlreadyExistException(ResponseCode.LOCATION_ALREADY_EXIST);
                });
    }

    public Place findById(Long id) {
        return placeRepository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException(ResponseCode.LOCATION_NOT_FOUND));
    }

    public void deleteById(Long id) {
        placeRepository.deleteById(id);
    }
}
