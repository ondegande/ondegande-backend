package org.backend.location.controller;

import java.net.URI;
import org.backend.location.application.LocationService;
import org.backend.location.domain.Location;
import org.backend.location.dto.LocationRequest;
import org.backend.location.dto.LocationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/locations")
    public ResponseEntity<LocationResponse> create(@RequestBody LocationRequest locationRequest) {
        if (locationRequest.name() == null ||
        locationRequest.longitude() == null ||
        locationRequest.latitude() == null) {
            return ResponseEntity.badRequest().build();
        }
        LocationResponse locationResponse = locationService.save(locationRequest);
        return ResponseEntity.created(URI.create("/location/" + locationResponse.id())).body(locationResponse);
     }

     @GetMapping("/locations/{id}")
    public ResponseEntity<Location> location(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.findById(id));
     }

     @DeleteMapping("/locations/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        locationService.deleteById(id);
        return ResponseEntity.noContent().build();
     }
}
