package com.chattingservice.domain.participant.exception;

import com.chattingservice.global.error.ErrorCode;
import com.chattingservice.global.error.exception.ValidationException;

public class InvalidRoleException extends ValidationException {

    public InvalidRoleException(ErrorCode errorCode) {
        super(errorCode);
    }
}
