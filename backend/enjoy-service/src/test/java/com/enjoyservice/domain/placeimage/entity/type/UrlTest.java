package com.enjoyservice.domain.placeimage.entity.type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UrlTest {

    @DisplayName("Url 객체가 생성된다.")
    @Test
    void from() {
        // given
        String value = "testUrl.com";
        // when then
        assertNotNull(Url.from(value));
    }
}