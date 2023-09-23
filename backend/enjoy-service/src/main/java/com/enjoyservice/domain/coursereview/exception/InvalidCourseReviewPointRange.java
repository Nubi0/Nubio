package com.enjoyservice.domain.coursereview.exception;

import com.enjoyservice.global.error.ErrorCode;
import com.enjoyservice.global.error.exception.ValidationException;

public class InvalidCourseReviewPointRange extends ValidationException {

    public InvalidCourseReviewPointRange(ErrorCode errorCode) {
        super(errorCode);
    }
}
