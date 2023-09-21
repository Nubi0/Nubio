package com.enjoyservice.domain.coursereview.exception;

import com.enjoyservice.global.error.ErrorCode;
import com.enjoyservice.global.error.exception.EntityNotFoundException;

public class CourseReviewNotFoundException extends EntityNotFoundException {
    public CourseReviewNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

}
