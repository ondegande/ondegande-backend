package org.backend.global.exception;

import org.backend.global.response.ResponseCode;

public class BaseException extends RuntimeException {

    private ResponseCode responseCode;

    public BaseException(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String getMessage() {
        return responseCode.getMessage();
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }
}
