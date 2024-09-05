package org.backend.travelcourse.exception;

import org.backend.global.exception.BaseException;
import org.backend.global.response.ResponseCode;

public class TravelCourseNotFoundException extends BaseException {

    public TravelCourseNotFoundException(ResponseCode responseCode) {
        super(responseCode);
    }
}
