package org.backend.place.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.backend.global.response.ApiResponse;
import org.backend.global.response.ResponseCode;
import org.backend.place.application.PlaceService;
import org.backend.place.domain.Place;
import org.backend.place.dto.PlaceRequest;
import org.backend.place.dto.PlaceResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Place Controller", description = "장소 정보에 대한 API")
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/places")
    @Operation(summary = "장소 정보를 저장", description = "사용자가 여행 장소를 여행코스에 추가하여 생성할 때 장소를 저장하기 위해 사용하는 API")
    public ApiResponse<PlaceResponse> create(@RequestBody PlaceRequest placeRequest) {
        if (placeRequest.title() == null ||
        placeRequest.mapx() == null ||
        placeRequest.mapy() == null) {
            return ApiResponse.fail(ResponseCode.BAD_REQUEST);
        }
        PlaceResponse placeResponse = placeService.save(placeRequest);
        return ApiResponse.success(ResponseCode.LOCATION_CREATED, placeResponse);
     }

    @GetMapping("/places/{id}")
    @Operation(summary = "장소 정보 불러오기", description = "Place 객체의 고유 ID 값을 사용하여 장소 정보를 불러오기 위해 사용하는 API")
    public ApiResponse<Place> location(@PathVariable Long id) {
        return ApiResponse.success(ResponseCode.LOCATION_READ_SUCCESS, placeService.findById(id));
     }

    @DeleteMapping("/places/{id}")
    @Operation(summary = "장소 정보 삭제하기", description = "Place 객체의 고유 ID 값을 사용하여 장소 정보를 불러오기 위해 사용하는 API")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        placeService.deleteById(id);
        return ApiResponse.success(ResponseCode.LOCATION_DELETE_SUCCESS);
     }
}
