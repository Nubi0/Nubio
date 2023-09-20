package com.enjoyservice.domain.course.exception;

import com.enjoyservice.global.error.ErrorCode;
import com.enjoyservice.global.error.exception.EntityNotFoundException;

public class CourseNotFoundException extends EntityNotFoundException {

    public CourseNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
