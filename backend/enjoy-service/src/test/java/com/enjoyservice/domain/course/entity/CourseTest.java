package com.enjoyservice.domain.course.entity;

import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.entity.type.Content;
import com.enjoyservice.domain.course.entity.type.PublicFlag;
import com.enjoyservice.domain.course.entity.type.Title;
import com.enjoyservice.domain.course.repository.CourseRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class CourseTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EntityManager em;

    @DisplayName("soft delete 되는지 확인")
    @Test
    void softDelete() {
        // given
        String memberId = "testMemberId";
        Course course = Course.builder()
                .title(Title.from("title"))
                .content(Content.from("content"))
                .region(Region.DAEGU)
                .publicFlag(PublicFlag.from(true))
                .memberId(memberId)
                .build();
        log.info("코스 객체 생성");

        Course savedCourse = courseRepository.saveAndFlush(course);
        log.info("course 저장");
        em.clear();

        List<Course> courses = courseRepository.findAll();
        em.flush();
        em.clear();
        log.info("course 전체 조회");
        int beforeCourseSize = courses.size();
        // when
        courseRepository.delete(savedCourse);
        em.flush();
        log.info("코스 삭제 : courseId = {}", savedCourse.getId());
        em.flush();
        em.clear();
        // then
        List<Course> resultCourses = courseRepository.findAll();
        int resultCourseSize = resultCourses.size();
        Optional<Course> opCourse = courseRepository.findById(savedCourse.getId());

        assertThat(resultCourseSize).isEqualTo(beforeCourseSize - 1);
        assertThat(opCourse).isEmpty();
    }
}