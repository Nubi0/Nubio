package com.enjoyservice.domain.coursereview.entity.type;

import com.enjoyservice.domain.coursereview.exception.InvalidCourseReviewPointRange;
import com.enjoyservice.global.error.ErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point {

    private static final int POINT_MIN = 0;
    private static final int POINT_MAX = 5;

    @Column(name = "point")
    @Lob
    private Integer value;

    private Point(final int value) {
        this.value = value;
    }

    public static Point from(final int value) {
        validate(value);
        return new Point(value);
    }

    private static void validate(final int value) {
        validateRange(value);
    }

    private static void validateRange(final int value) {
        if(value < POINT_MIN || value > POINT_MAX) {
            throw new InvalidCourseReviewPointRange(ErrorCode.INVALID_COURSE_REVIEW_POINT_RANGE);
        }
    }
}
