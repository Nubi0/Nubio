package com.enjoyservice.domain.coursereview.entity.type;

import com.enjoyservice.domain.coursereview.exception.InvalidCourseReviewPointRange;
import com.enjoyservice.domain.coursereview.entity.type.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PointTest {

    @DisplayName("코스 리뷰 점수가 0 ~ 5점이면 객체가 생성된다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void from(int input) {
        assertNotNull(Point.from(input));
    }

    @DisplayName("코스 리뷰 점수가 0 ~ 5점이 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-2, -1, 6, 7, 8, 9})
    void invalidRange(int input) {
        assertThatThrownBy(() -> Point.from(input))
                .isInstanceOf(InvalidCourseReviewPointRange.class);
    }
}