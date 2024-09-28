package org.backend.global.exception;

import org.backend.global.response.ResponseCode;

public class BadRequestException extends BaseException {

    public BadRequestException(ResponseCode responseCode) {
        super(responseCode);
    }
}
