package com.enjoyservice.domain.coursereview.entity.type;

import com.enjoyservice.domain.coursereview.entity.type.Content;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContentTest {

    @DisplayName("코스 리뷰 내용 객체가 생성된다.")
    @Test
    void from() {
        // given
        String value = "original_name";
        // when then
        assertNotNull(Content.from(value));
    }
}