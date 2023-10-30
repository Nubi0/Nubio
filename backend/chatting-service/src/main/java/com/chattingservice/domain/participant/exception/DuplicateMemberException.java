package com.chattingservice.domain.participant.exception;

import com.chattingservice.global.error.ErrorCode;
import com.chattingservice.global.error.exception.BusinessException;

public class DuplicateMemberException extends BusinessException {

    public DuplicateMemberException(ErrorCode errorCode) {
        super(errorCode);
    }

}
