package com.authenticationservice.api.auth.exception;

import com.authenticationservice.domain.member.exception.InvalidBirthFormatException;
import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.error.exception.BusinessException;
import com.authenticationservice.global.error.exception.InvalidException;
import com.authenticationservice.global.error.exception.ValidationException;

public class InvalidPasswordException extends InvalidException {

    public InvalidPasswordException(ErrorCode errorCode) {
        super(errorCode);
    }

}

