package com.enjoyservice.domain.course.entity.type;

import com.enjoyservice.domain.place.entity.type.Active;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PublicFlagTest {

    @DisplayName("Course의 PublicFlag 객체가 생성된다.")
    @Test
    void from() {
        // given
        boolean value = true;
        // when then
        assertNotNull(PublicFlag.from(value));
    }
}