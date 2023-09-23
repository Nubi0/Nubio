package com.safeservice.global.error.exception;

import com.safeservice.global.error.ErrorCode;

public class MisMatchException extends BusinessException{

    public MisMatchException(ErrorCode errorCode){super(errorCode);}

}
