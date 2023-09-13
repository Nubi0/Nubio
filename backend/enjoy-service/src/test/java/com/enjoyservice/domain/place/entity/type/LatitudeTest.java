package com.enjoyservice.domain.place.entity.type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LatitudeTest {

    @DisplayName("주소의 경도 객체가 생성된다.")
    @Test
    void from() {
        // given
        Double value = 126.94831126744977;
        // when then
        assertNotNull(Latitude.from(value));
    }
}