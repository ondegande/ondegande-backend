package org.backend.location.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.backend.global.response.ApiResponse;
import org.backend.global.response.ResponseCode;
import org.backend.location.application.LocationService;
import org.backend.location.domain.Location;
import org.backend.location.dto.LocationRequest;
import org.backend.location.dto.LocationResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Location Controller", description = "위치 정보에 대한 API")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/locations")
    @Operation(summary = "위치 정보를 저장", description = "사용자가 여행 위치를 여행코스에 추가하여 생성할 때 위치를 저장하기 위해 사용하는 API")
    public ApiResponse<LocationResponse> create(@RequestBody LocationRequest locationRequest) {
        if (locationRequest.name() == null ||
        locationRequest.longitude() == null ||
        locationRequest.latitude() == null) {
            return ApiResponse.fail(ResponseCode.BAD_REQUEST);
        }
        LocationResponse locationResponse = locationService.save(locationRequest);
        return ApiResponse.success(ResponseCode.LOCATION_CREATED, locationResponse);
     }

    @GetMapping("/locations/{id}")
    @Operation(summary = "위치 정보 불러오기", description = "Location 객체의 고유 ID 값을 사용하여 위치 정보를 불러오기 위해 사용하는 API")
    public ApiResponse<Location> location(@PathVariable Long id) {
        return ApiResponse.success(ResponseCode.LOCATION_READ_SUCCESS, locationService.findById(id));
     }

    @DeleteMapping("/locations/{id}")
    @Operation(summary = "위치 정보 삭제하기", description = "Location 객체의 고유 ID 값을 사용하여 위치 정보를 불러오기 위해 사용하는 API")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        locationService.deleteById(id);
        return ApiResponse.success(ResponseCode.LOCATION_DELETE_SUCCESS);
     }
}
