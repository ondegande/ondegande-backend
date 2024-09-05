package org.backend.travelcourse.exception;

import java.time.LocalDateTime;
import org.backend.global.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class TravelCourseExceptionHandler {

    @ExceptionHandler(TravelCourseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTravelCourseNotFoundException(TravelCourseNotFoundException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                exception.getResponseCode().getHttpStatus(),
                exception.getResponseCode().name(),
                exception.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, exception.getResponseCode().getHttpStatus());
    }

    @ExceptionHandler(TravelCourseBadRequestException.class)
    public ResponseEntity<ErrorResponse> handleTravelCourseBadRequestException(TravelCourseBadRequestException exception, WebRequest request) {
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
