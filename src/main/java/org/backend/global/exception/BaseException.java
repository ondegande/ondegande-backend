package org.backend.global.exception;

import org.backend.global.response.ResponseCode;

public class BaseException extends RuntimeException {

    private ResponseCode responseCode;

    public BaseException(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }
}
