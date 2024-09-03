package org.backend.travelcoursedetail.excetion;

import org.backend.global.exception.BaseException;
import org.backend.global.response.ResponseCode;

public class TravelCourseDetailNotFoundException extends BaseException {

    public TravelCourseDetailNotFoundException(ResponseCode responseCode) {
        super(responseCode);
    }
}
