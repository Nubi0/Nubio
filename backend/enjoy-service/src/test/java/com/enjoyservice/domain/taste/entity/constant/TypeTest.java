package com.enjoyservice.domain.taste.entity.constant;

import com.enjoyservice.domain.taste.exception.InvalidDetailTasteTypeException;
import com.enjoyservice.domain.taste.exception.InvalidTasteTypeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TypeTest {

    @DisplayName("취향이 맞으면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"먹기", "놀기", "마시기"})
    void from(String input) {
        assertThatCode(() -> Type.from(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("취향이 맞지 않으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"자기", "쉬기", "가만히 있기"})
    void canNotFrom(String input) {
        assertThatThrownBy(() -> Type.from(input))
                .isInstanceOf(InvalidTasteTypeException.class);
    }
}