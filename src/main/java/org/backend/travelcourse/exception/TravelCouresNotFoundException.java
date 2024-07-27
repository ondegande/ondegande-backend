package org.backend.travelcourse.exception;

import org.backend.global.exception.BaseException;
import org.backend.global.response.ResponseCode;

public class TravelCouresNotFoundException extends BaseException {

    public TravelCouresNotFoundException(ResponseCode responseCode) {
        super(responseCode);
    }
}
