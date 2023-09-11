package com.enjoyservice.domain.place.entity.constant;

import com.enjoyservice.domain.place.exception.InvalidGroupCodeException;
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

    @DisplayName("GroupCode가 맞으면 GroupCode 객체를 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"CS2", "PK6", "OL7", "CT1", "AT4", "AD5", "FD6", "CD7"})
    void from(String input) {
        // when
        GroupCode groupCode = GroupCode.from(input);
        // then
        assertThat(groupCode).isEqualTo(GroupCode.valueOf(input));
    }

    @DisplayName("GroupCode가 맞으면 통과한다.")
    @ParameterizedTest
    @ValueSource(strings = {"CS2", "PK6", "OL7", "CT1", "AT4", "AD5", "FD6", "CD7"})
    void isGroupCode(String input) {
        assertTrue(GroupCode.isGroupCode(input));
    }

    @DisplayName("GroupCode가 아니면 false를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"AB2", "P6", "7", "QWER"})
    void isNotGroupCode(String input) {
        assertFalse(GroupCode.isGroupCode(input));
    }
}