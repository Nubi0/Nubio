package com.enjoyservice.domain.place.entity.type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActiveTest {

    @DisplayName("Place의 Active 객체가 생성된다.")
    @Test
    void from() {
        // given
        boolean value = true;
        // when then
        assertNotNull(Active.from(value));
    }
}