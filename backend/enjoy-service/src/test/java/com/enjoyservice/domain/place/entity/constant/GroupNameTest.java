package com.enjoyservice.domain.place.entity.constant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupNameTest {

    @DisplayName("GroupName이 맞으면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"편의점", "주차장", "문화시설", "관광명소", "숙박", "음식점", "카페"})
    void isGroupCode(String input) {
        assertTrue(GroupName.isGroupName(input));
    }

    @DisplayName("GroupName이 아니면 false를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"AB2", "P6", "7", "QWER"})
    void isNotGroupCode(String input) {
        assertFalse(GroupName.isGroupName(input));
    }

    @DisplayName("GroupName이 맞으면 GroupName 객체를 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"편의점", "주차장", "문화시설", "관광명소", "숙박", "음식점", "카페"})
    void from(String input) {
        // when
        GroupName groupName = GroupName.from(input);
        // then
        assertThat(groupName).isEqualTo(GroupName.valueOf(input));
    }

}