package org.backend.location.application;

import org.backend.global.response.ResponseCode;
import org.backend.location.dto.LocationRequest;
import org.backend.location.dto.LocationResponse;
import org.backend.location.domain.Location;
import org.backend.location.domain.LocationRepository;
import org.backend.location.exception.LocationAlreadyExistException;
import org.backend.location.exception.LocationNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Transactional
    public LocationResponse save(LocationRequest request) {
        validateLocation(request);

        Location location = locationRepository.save(request.toEntity());

        return new LocationResponse(
                location.getId(),
                location.getName(),
                location.getLatitude(),
                location.getLongitude()
        );
    }

    private void validateLocation(LocationRequest request) {
        locationRepository.findByLatitudeAndLongitude(request.latitude(), request.longitude())
                .ifPresent(it -> {
                    throw new LocationAlreadyExistException(ResponseCode.LOCATION_ALREADY_EXIST);
                });
    }

    public Location findById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(ResponseCode.LOCATION_NOT_FOUND));
    }

    public void deleteById(Long id) {
        locationRepository.deleteById(id);
    }
}
