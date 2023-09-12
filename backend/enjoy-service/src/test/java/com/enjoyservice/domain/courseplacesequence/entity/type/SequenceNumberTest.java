package com.enjoyservice.domain.courseplacesequence.entity.type;

import com.enjoyservice.domain.course.entity.type.Title;
import com.enjoyservice.domain.courseplacesequence.entity.exception.InvalidSequenceNumberException;
import com.enjoyservice.domain.placereview.entity.type.Point;
import com.enjoyservice.domain.placereview.exception.InvalidPlaceReviewPointRange;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SequenceNumberTest {

    @DisplayName("코스에 속한 장소의 순서가 0이상 이면 객체가 생성된다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 100})
    void from(int input) {
        assertNotNull(SequenceNumber.from(input));
    }

    @DisplayName("코스에 속한 장소의 순서가 0 미만 이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -3, -100})
    void invalidRange(int input) {
        assertThatThrownBy(() -> SequenceNumber.from(input))
                .isInstanceOf(InvalidSequenceNumberException.class);
    }
}