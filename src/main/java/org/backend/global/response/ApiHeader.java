package org.backend.global.response;

import org.springframework.http.HttpStatus;

public class ApiHeader {

    private HttpStatus status;
    private String message;

    public ApiHeader(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
