package com.enjoyservice.domain.place.entity.constant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupCodeTest {

    @DisplayName("")
    @Test
    void from() {
    }

    @DisplayName("GroupCode가 맞으면 통과한다.")
    @ParameterizedTest
    @ValueSource(strings = {"CS2", "PK6", "OL7", "CT1", "AT4", "AD5", "FD6", "CD7"})
    void isGroupCode(String input) {
        // when
        boolean result = GroupCode.isGroupCode(input);
        // then
        assertThat(result).isTrue();
    }
}