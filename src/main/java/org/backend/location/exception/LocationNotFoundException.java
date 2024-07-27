package org.backend.location.exception;

import org.backend.global.exception.BaseException;
import org.backend.global.response.ResponseCode;

public class LocationNotFoundException extends BaseException {

    public LocationNotFoundException(ResponseCode responseCode) {
        super(responseCode);
    }
}
