package org.backend.global.config.exception;

import java.time.LocalDateTime;
import org.apache.coyote.BadRequestException;
import org.backend.global.response.ApiResponse;
import org.backend.global.response.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionController {

//    @ExceptionHandler(.class)
//    public ApiResponse<ErrorResponse> handleBadRequestException(BadRequestException exception, WebRequest request) {
//        ErrorResponse errorResponse = new ErrorResponse(
//                LocalDateTime.now(),
//                ResponseCode.BAD_REQUEST,
//                exception.getMessage(),
//                request.getDescription(false)
//        );
//    }
}
