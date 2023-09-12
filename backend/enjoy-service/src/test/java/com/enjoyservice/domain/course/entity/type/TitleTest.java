package com.enjoyservice.domain.course.entity.type;

import com.enjoyservice.domain.place.entity.type.Url;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TitleTest {

    @DisplayName("코스의 제목 객체가 생성된다.")
    @Test
    void from() {
        // given
        String value = "title";
        // when then
        assertNotNull(Title.from(value));
    }
}