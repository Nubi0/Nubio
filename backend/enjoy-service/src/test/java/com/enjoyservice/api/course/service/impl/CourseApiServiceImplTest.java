package com.enjoyservice.api.course.service.impl;

import com.enjoyservice.api.course.dto.CourseCreateReq;
import com.enjoyservice.api.course.service.CourseApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@Transactional
public class CourseApiServiceImplTest {

    @Autowired
    private CourseApiService courseApiService;

    @DisplayName("코스 생성 boot test")
    @Test
    void test() {
        // given
        String tagName1 = "tagName1";
        String tagName2 = "tagName2";
        String tagName3 = "tagName3";
        CourseCreateReq request = CourseCreateReq.builder()
                .title("reqTitle")
                .content("reqContent")
                .region("DAEGU")
                .publicFlag(true)
                .courseTags(List.of(new CourseCreateReq.TagInfo(tagName1),
                        new CourseCreateReq.TagInfo(tagName2),
                        new CourseCreateReq.TagInfo(tagName3)))
                .placeInfos(List.of(new CourseCreateReq.PlaceInfo(1L, 1),
                        new CourseCreateReq.PlaceInfo(2L, 2),
                        new CourseCreateReq.PlaceInfo(3L, 3)))
                .build();
        String memberId = "memberId";
        // when then
        assertDoesNotThrow(() -> courseApiService.createCourse(request, memberId));
    }
}
