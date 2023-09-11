package com.safeservice.domain.report.exception;

import com.safeservice.global.error.ErrorCode;
import com.safeservice.global.error.exception.MisMatchException;

public class MisMatchIdentification extends MisMatchException {

    public MisMatchIdentification(ErrorCode errorCode) {
        super(errorCode);
    }
}
