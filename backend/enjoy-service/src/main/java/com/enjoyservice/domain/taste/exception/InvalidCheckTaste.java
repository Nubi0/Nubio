package com.enjoyservice.domain.taste.exception;

import com.enjoyservice.global.error.ErrorCode;
import com.enjoyservice.global.error.exception.ValidationException;

public class InvalidCheckTaste extends ValidationException {
    public InvalidCheckTaste(ErrorCode errorCode) {
        super(errorCode);
    }
}
