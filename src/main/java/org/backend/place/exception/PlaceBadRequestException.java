package org.backend.place.exception;

import org.backend.global.exception.BaseException;
import org.backend.global.response.ResponseCode;

public class PlaceBadRequestException extends BaseException {

    public PlaceBadRequestException(ResponseCode responseCode) {
        super(responseCode);
    }
}
