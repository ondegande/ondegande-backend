package org.backend.place.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.backend.place.domain.Place;
import org.backend.place.domain.PlaceRepository;
import org.backend.place.dto.PlaceRequest;
import org.backend.place.dto.PlaceResponse;
import org.backend.place.exception.PlaceAlreadyExistException;
import org.backend.place.exception.PlaceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("local")
public class PlaceServiceTest {

    @Mock
    private PlaceRepository placeRepository;

    @InjectMocks
    private PlaceService placeService;

    private PlaceRequest placeRequest;
    private Place place;

    @BeforeEach
    void setUp() {
        placeRequest = new PlaceRequest(
                "장소1",
                15.3333,
                15.4444);

        place = new Place(
                1L,
                "장소1",
                15.3333,
                15.4444);
    }

    @Test
    @DisplayName("장소 정보 저장을 테스트합니다.")
    void testSavePlace() {
        // when
        doReturn(place).when(placeRepository).save(any(Place.class));
        PlaceResponse response = placeService.save(placeRequest);

        // then
        assertNotNull(response);
        assertEquals(response.placeName(), placeRequest.placeName());
    }

    @Test
    @DisplayName("장소 정보 조회를 테스트합니다.")
    void testGetPlace() {
        // when
        when(placeRepository.findById(1L)).thenReturn(Optional.of(place));
        PlaceResponse response = placeService.findById(1L);

        // then
        assertNotNull(response);
        assertEquals(response.longitude(), 15.4444);
    }

    @Test
    @DisplayName("장소 정보 조회 시 찾을 수 없는 예외 발생 테스트 입니다.")
    void testFindByIdException() {
        // when
        when(placeRepository.findById(1L)).thenThrow(PlaceNotFoundException.class);

        // then
        assertThrows(PlaceNotFoundException.class,
                () -> placeService.findById(1L));
    }

    @Test
    @DisplayName("장소 정보 조회 시 장소 정보가 이미 존재하는지 검증하는 테스트를 진행합니다.")
    void testValidateLocationPlace() {
        // when
        placeService.validatePlace(placeRequest);

        // then
        verify(placeRepository).findByLatitudeAndLongitude(place.getLatitude(), place.getLongitude());
    }

    @Test
    @DisplayName("장소 정보를 삭제를 테스트합니다.")
    void testDeletePlace() {
        // when
        when(placeRepository.findById(1L)).thenReturn(Optional.of(place));
        placeService.deleteById(1L);

        // then
        verify(placeRepository).deleteById(1L);
    }

    @Test
    @DisplayName("장소 정보 삭제 시 장소 정보가 존재하지 않는 예외 처리 테스트를 진행합니다.")
    void testDeletePlaceException() {
        // when
        when(placeRepository.findById(1L)).thenReturn(Optional.empty());

        // then
        assertThrows(PlaceNotFoundException.class, () -> placeService.deleteById(1L));
        verify(placeRepository, never()).deleteById(1L);
    }

    @Test
    @DisplayName("장소 정보가 이미 존재하는 예외 발생 테스트 입니다.")
    void testValidateLocationException() {
        // when
        when(placeRepository.findByLatitudeAndLongitude(place.getLatitude(), place.getLongitude()))
                .thenThrow(PlaceAlreadyExistException.class);

        // then
        assertThrows(PlaceAlreadyExistException.class,
                () -> placeService.validatePlace(placeRequest));
    }
}
