package com.enjoyservice.domain.place.entity.type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoadNameTest {

    @DisplayName("주소의 도로명 객체가 생성된다.")
    @Test
    void from() {
        // given
        String value = "경기 수원시 권선구 서수원로577번길 171";
        // when then
        assertNotNull(RoadName.from(value));
    }
}