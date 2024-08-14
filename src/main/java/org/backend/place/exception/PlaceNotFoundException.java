package org.backend.place.exception;

import org.backend.global.exception.BaseException;
import org.backend.global.response.ResponseCode;

public class PlaceNotFoundException extends BaseException {

    public PlaceNotFoundException(ResponseCode responseCode) {
        super(responseCode);
    }
}
