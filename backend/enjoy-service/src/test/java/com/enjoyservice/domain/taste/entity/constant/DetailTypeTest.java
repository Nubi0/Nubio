package com.enjoyservice.domain.taste.entity.constant;

import com.enjoyservice.domain.taste.exception.InvalidDetailTasteTypeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DetailTypeTest {

    @DisplayName("상세 취향이 맞으면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"한식", "노래방", "커피"})
    void from(String input) {
        assertThatCode(() -> DetailType.from(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("상세 취향이 맞지 않으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"고기", "스포츠", "담배"})
    void canNotFrom(String input) {
        assertThatThrownBy(() -> DetailType.from(input))
                .isInstanceOf(InvalidDetailTasteTypeException.class);
    }

}