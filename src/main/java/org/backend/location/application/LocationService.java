package org.backend.location.application;

import org.backend.location.dto.LocationRequest;
import org.backend.location.dto.LocationResponse;
import org.backend.location.domain.Location;
import org.backend.location.domain.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

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
                    throw new IllegalArgumentException("이미 존재하는 장소입니다.");
                });
    }

    public Location findById(Long id) {
        return locationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 장소입니다."));
    }

    public void deleteById(Long id) {
        locationRepository.deleteById(id);
    }
}
