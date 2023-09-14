package com.enjoyservice.domain.place.exception;

import com.enjoyservice.global.error.ErrorCode;
import com.enjoyservice.global.error.exception.ValidationException;

public class InvalidPhoneNumberFormatException extends ValidationException {

    public InvalidPhoneNumberFormatException(ErrorCode errorCode) {
        super(errorCode);
    }
}
