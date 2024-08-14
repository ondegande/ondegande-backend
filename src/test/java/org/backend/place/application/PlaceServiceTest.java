package org.backend.place.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.backend.place.domain.Place;
import org.backend.place.domain.PlaceRepository;
import org.backend.place.domain.PlaceType;
import org.backend.place.dto.PlaceRequest;
import org.backend.place.dto.PlaceResponse;
import org.backend.place.exception.PlaceAlreadyExistException;
import org.backend.place.exception.PlaceNotFoundException;
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

    @Test
    @DisplayName("장소 정보 저장을 테스트합니다.")
    void testSavePlace() {
        // given
        PlaceRequest request = new PlaceRequest(
                "10001",
                "Sample Place 1",
                PlaceType.ACCOMMODATION,
                null,
                null,
                null,
                null,
                null);
        Place placeEntity = request.toEntity();

        // when
        doReturn(placeEntity).when(placeRepository).save(any(Place.class));
        PlaceResponse response = placeService.save(request);

        // then
        assertNotNull(response);
        assertEquals(response.contentid(), request.contentid());
    }

    @Test
    @DisplayName("장소 정보 조회를 테스트합니다.")
    void testGetPlace() {
        // given
        Place place = new Place(1L,
                "10001",
                "Sample Place 1",
                PlaceType.ACCOMMODATION,
                null,
                null,
                null,
                null,
                null);

        // when
        when(placeRepository.findById(1L)).thenReturn(Optional.of(place));
        PlaceResponse response = placeService.findById(1L);

        // then
        assertNotNull(response);
        assertEquals(response.contentid(), "10001");
    }

    @Test
    @DisplayName("장소 정보 조회 시 찾을 수 없는 예외 발생 테스트 입니다.")
    void testFindByIdException() {
        // given
        Place place = new Place(1L,
                "10001",
                "Sample Place 1",
                PlaceType.ACCOMMODATION,
                null,
                null,
                null,
                null,
                null);

        // when
        when(placeRepository.findById(1L)).thenThrow(PlaceNotFoundException.class);

        // then
        assertThrows(PlaceNotFoundException.class,
                () -> placeService.findById(1L));
    }

    @Test
    @DisplayName("장소 정보 조회 시 장소 정보가 이미 존재하는지 검증하는 테스트를 진행합니다.")
    void testValidateLocationPlace() {
        // given
        PlaceRequest request = new PlaceRequest(
                "10001",
                "Sample Place 1",
                PlaceType.ACCOMMODATION,
                null,
                null,
                null,
                null,
                null);
        Place place = request.toEntity();

        // when
        placeService.validatePlace(request);

        // then
        verify(placeRepository).findByLatitudeAndLongitude(place.getMapx(), place.getMapy());
    }

    @Test
    @DisplayName("장소 정보를 삭제를 테스트합니다.")
    void testDeletePlace() {
        // given
        Long placeId = 1L;
        Place place = new Place(1L,
                "10001",
                "Sample Place 1",
                PlaceType.ACCOMMODATION,
                null,
                null,
                null,
                null,
                null);

        // when
        when(placeRepository.findById(placeId)).thenReturn(Optional.of(place));
        placeService.deleteById(placeId);

        // then
        verify(placeRepository).deleteById(placeId);
    }

    @Test
    @DisplayName("장소 정보 삭제 시 장소 정보가 존재하지 않는 예외 처리 테스트를 진행합니다.")
    void testDeletePlaceException() {
        // given
        Long placeId = 1L;

        // when
        when(placeRepository.findById(placeId)).thenReturn(Optional.empty());

        // then
        assertThrows(PlaceNotFoundException.class, () -> placeService.deleteById(placeId));
        verify(placeRepository, never()).deleteById(placeId);
    }

    @Test
    @DisplayName("장소 정보가 이미 존재하는 예외 발생 테스트 입니다.")
    void testValidateLocationException() {
        PlaceRequest request = new PlaceRequest(
                "10001",
                "Sample Place 1",
                PlaceType.ACCOMMODATION,
                null,
                null,
                null,
                null,
                null);
        Place place = request.toEntity();

        // when
        when(placeRepository.findByLatitudeAndLongitude(place.getMapx(), place.getMapy())).thenThrow(
                PlaceAlreadyExistException.class);

        // then
        assertThrows(PlaceAlreadyExistException.class,
                () -> placeService.validatePlace(request));
    }
}
