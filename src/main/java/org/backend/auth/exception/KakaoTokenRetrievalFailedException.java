package org.backend.auth.exception;

import org.backend.global.exception.BaseException;
import org.backend.global.response.ResponseCode;

public class KakaoTokenRetrievalFailedException extends BaseException {

    public KakaoTokenRetrievalFailedException(ResponseCode response) {
        super(response);
    }
}
