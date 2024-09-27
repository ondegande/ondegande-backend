package org.backend.global.exception;

import org.backend.global.response.ResponseCode;

public class AlreadyExistException extends BaseException {

    public AlreadyExistException(ResponseCode responseCode) {
        super(responseCode);
    }
}
