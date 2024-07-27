package org.backend.location.exception;

import org.backend.global.exception.BaseException;
import org.backend.global.response.ResponseCode;

public class LocationAlreadyExistException extends BaseException {

    public LocationAlreadyExistException(ResponseCode responseCode) {
        super(responseCode);
    }
}
