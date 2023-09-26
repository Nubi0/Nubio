package com.authenticationservice.domain.profile.exception;


import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.error.exception.ValidationException;

public class InvalidFileSizeException extends ValidationException {

    public InvalidFileSizeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
