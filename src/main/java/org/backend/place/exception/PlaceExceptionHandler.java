package org.backend.place.exception;

import java.time.LocalDateTime;
import org.backend.global.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class PlaceExceptionHandler {

    @ExceptionHandler(PlaceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlaceException(PlaceNotFoundException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                exception.getResponseCode().getHttpStatus(),
                exception.getResponseCode().name(),
                exception.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, exception.getResponseCode().getHttpStatus());
    }

    @ExceptionHandler(PlaceAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handlePlaceAlreadyExistException(PlaceAlreadyExistException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                exception.getResponseCode().getHttpStatus(),
                exception.getResponseCode().name(),
                exception.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, exception.getResponseCode().getHttpStatus());
    }

    @ExceptionHandler(PlaceBadRequestException.class)
    public ResponseEntity<ErrorResponse> handlePlaceBadRequestException(PlaceBadRequestException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                exception.getResponseCode().getHttpStatus(),
                exception.getResponseCode().name(),
                exception.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, exception.getResponseCode().getHttpStatus());
    }
}
