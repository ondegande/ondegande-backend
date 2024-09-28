package org.backend.global.response;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp,
                            String message,
                            String path) {
    @Override
    public String toString() {
        return "time : " + timestamp + ", message : " + message + " path : " + path;
    }
}
