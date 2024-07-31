package org.backend.auth.exception;

import org.backend.global.exception.BaseException;
import org.backend.global.response.ResponseCode;

public class KakaoUserRetrievalFailedException extends BaseException {

    public KakaoUserRetrievalFailedException(ResponseCode response) {
        super(response);
    }
}
