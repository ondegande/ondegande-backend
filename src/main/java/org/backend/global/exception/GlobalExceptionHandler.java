package org.backend.global.exception;

import java.time.LocalDateTime;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.backend.global.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    protected final Log log = LogFactory.getLog(this.getClass());

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                exception.getResponseCode().getMessage(),
                request.getDescription(false)
        );
        log.warn(errorResponse.toString(), exception);
        return new ResponseEntity<>(errorResponse, exception.getResponseCode().getHttpStatus());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                exception.getResponseCode().getMessage(),
                request.getDescription(false)
        );
        log.warn(errorResponse.toString(), exception);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistException(AlreadyExistException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                exception.getResponseCode().getMessage(),
                request.getDescription(false)
        );
        log.warn(errorResponse.toString(), exception);
        return new ResponseEntity<>(errorResponse, exception.getResponseCode().getHttpStatus());
    }
}
