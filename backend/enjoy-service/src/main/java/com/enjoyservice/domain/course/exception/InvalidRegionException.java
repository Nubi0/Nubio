package com.enjoyservice.domain.course.exception;

import com.enjoyservice.global.error.ErrorCode;
import com.enjoyservice.global.error.exception.ValidationException;

public class InvalidRegionException extends ValidationException {

    public InvalidRegionException(ErrorCode errorCode) {
        super(errorCode);
    }
}
