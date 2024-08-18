package org.backend.travelcourse.exception;

import org.backend.global.exception.BaseException;
import org.backend.global.response.ResponseCode;

public class TravelCourseBadRequestException extends BaseException {

    public TravelCourseBadRequestException(ResponseCode response) {
        super(response);
    }
}
