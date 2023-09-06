package com.authenticationservice.domain.member.exception;

import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.error.exception.BusinessException;

public class InvalidBirthFormatException extends BusinessException {

    public InvalidBirthFormatException(ErrorCode errorCode) {
        super(errorCode);
    }
}
