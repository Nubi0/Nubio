package com.enjoyservice.domain.placereviewimage.entity.type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NameTest {

    @DisplayName("장소 리뷰 사진 이름 객체가 생성된다.")
    @Test
    void from() {
        // given
        String value = "original_name";
        // when then
        assertNotNull(Name.from(value));
    }
}