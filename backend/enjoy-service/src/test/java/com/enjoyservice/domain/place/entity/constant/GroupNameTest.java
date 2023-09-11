package com.enjoyservice.domain.place.entity.constant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupNameTest {

    @DisplayName("GroupName이 맞으면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"편의점", "주차장", "문화시설", "관광명소", "숙박", "음식점", "카페"})
    void isGroupCode(String input) {
        assertTrue(GroupName.isGroupName(input));
    }

}