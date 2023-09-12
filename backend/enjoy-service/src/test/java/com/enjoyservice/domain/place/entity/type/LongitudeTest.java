package com.enjoyservice.domain.place.entity.type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class LongitudeTest {

    @DisplayName("주소의 위도 객체가 생성된다.")
    @Test
    void from() {
        // given
        Double value = 37.27629056901903;
        // when then
        assertNotNull(Longitude.from(value));
    }
}