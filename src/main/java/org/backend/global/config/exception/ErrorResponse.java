package org.backend.global.config.exception;

import java.time.LocalDateTime;
import org.backend.global.response.ResponseCode;

public record ErrorResponse(
        LocalDateTime timestamp,
        ResponseCode responseCode,
        String error,
        String path
) {
}
