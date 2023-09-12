package com.enjoyservice.domain.tag.entity.type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NameTest {

    @DisplayName("태그의 이름 객체가 생성된다.")
    @Test
    void from() {
        // given
        String value = "tag_name";
        // when then
        assertNotNull(Name.from(value));
    }
}