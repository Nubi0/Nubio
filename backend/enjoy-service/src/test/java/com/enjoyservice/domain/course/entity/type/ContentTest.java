package com.enjoyservice.domain.course.entity.type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContentTest {

    @DisplayName("코스의 내용 객체가 생성된다.")
    @Test
    void from() {
        // given
        String value = "content";
        // when then
        assertNotNull(Content.from(value));
    }
}