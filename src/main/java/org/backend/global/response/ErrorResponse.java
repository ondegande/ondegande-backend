package org.backend.global.response;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public record ErrorResponse(LocalDateTime timestamp,
                            HttpStatus httpStatus,
                            String message,
                            String error,
                            String path) {
}
