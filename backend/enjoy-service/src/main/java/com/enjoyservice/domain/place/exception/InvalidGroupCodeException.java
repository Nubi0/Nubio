package com.enjoyservice.domain.place.exception;

import com.enjoyservice.global.error.ErrorCode;
import com.enjoyservice.global.error.exception.ValidationException;

public class InvalidGroupCodeException extends ValidationException {

    public InvalidGroupCodeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
