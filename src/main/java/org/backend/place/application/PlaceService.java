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
        validatePlace(request);
        Place place = placeRepository.save(request.toEntity());
        return PlaceResponse.toResponseDto(place);
    }

    @Transactional(readOnly = true)
    public PlaceResponse findById(Long id) {
        return PlaceResponse.toResponseDto(placeRepository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException(ResponseCode.LOCATION_NOT_FOUND)));
    }

    public void deleteById(Long id) {
        placeRepository.findById(id).orElseThrow(
                () -> new PlaceNotFoundException(ResponseCode.LOCATION_NOT_FOUND));
        placeRepository.deleteById(id);
    }

    public void validatePlace(PlaceRequest request) {
        placeRepository.findByLatitudeAndLongitude(request.mapx(), request.mapy())
                .ifPresent(it -> {
                    throw new PlaceAlreadyExistException(ResponseCode.LOCATION_ALREADY_EXIST);
                });
    }
}
