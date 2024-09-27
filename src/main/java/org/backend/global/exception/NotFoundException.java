package org.backend.global.exception;

import org.backend.global.response.ResponseCode;

public class NotFoundException extends BaseException {

    public NotFoundException(ResponseCode responseCode) {
        super(responseCode);
    }
}
