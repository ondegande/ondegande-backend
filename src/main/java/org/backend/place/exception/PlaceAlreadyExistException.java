package org.backend.place.exception;

import org.backend.global.exception.BaseException;
import org.backend.global.response.ResponseCode;

public class PlaceAlreadyExistException extends BaseException {

    public PlaceAlreadyExistException(ResponseCode responseCode) {
        super(responseCode);
    }
}
