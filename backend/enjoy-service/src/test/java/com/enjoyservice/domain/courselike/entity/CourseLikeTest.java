package com.enjoyservice.domain.courselike.entity;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.entity.type.Content;
import com.enjoyservice.domain.course.entity.type.PublicFlag;
import com.enjoyservice.domain.course.entity.type.Title;
import com.enjoyservice.domain.course.repository.CourseRepository;
import com.enjoyservice.domain.courselike.repository.CourseLikeRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
class CourseLikeTest {

    @Autowired
    private CourseLikeRepository courseLikeRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EntityManager em;

    private List<Course> savedBeforeCourse;

    @BeforeEach
    void setUp() {
        for(int i = 1; i <= 3; i++) {
            Course course = generateCourse(i, Region.DAEGU);
            courseRepository.saveAndFlush(course);
        }
        em.clear();
        savedBeforeCourse = courseRepository.findAll();
    }

    private Course generateCourse(int index, Region region) {
        return Course.builder()
                .title(Title.from("courseTitle" + index))
                .content(Content.from("courseContent" + index))
                .region(region)
                .publicFlag(PublicFlag.from(true))
                .memberId("memberId" + index)
                .build();
    }

    @DisplayName("CourseLike soft delete 확인")
    @Test
    void softDelete() {
        // given
        String memberId = "memberId";
        Course course = savedBeforeCourse.get(0);
        CourseLike courseLike = CourseLike.builder()
                .course(course)
                .memberId(memberId)
                .build();
        CourseLike savedCourseLike = courseLikeRepository.saveAndFlush(courseLike);
        em.clear();
        log.info("CourseLike 저장 완료");
        // when
        courseLikeRepository.delete(savedCourseLike);
        em.flush();
        em.clear();
        log.info("CourseLike 삭제 완료");
        Optional<CourseLike> opCourseLike = courseLikeRepository.findById(savedCourseLike.getId());
        // then
        assertThat(opCourseLike).isNotEmpty();
        assertThat(opCourseLike.get().getActive().isValue()).isFalse();
    }

    @DisplayName("CourseLike Active를 false로 전환")
    @Test
    void changeActiveValueToFalse() {
        // given
        String memberId = "memberId";
        Course course = savedBeforeCourse.get(0);
        CourseLike courseLike = CourseLike.builder()
                .course(course)
                .memberId(memberId)
                .build();
        CourseLike savedCourseLike = courseLikeRepository.saveAndFlush(courseLike);
        em.clear();
        log.info("CourseLike 저장 완료");
        // when
        boolean result = savedCourseLike.changeActiveValue();
        // then
        assertFalse(result);
    }

    @DisplayName("CourseLike Active를 true로 전환")
    @Test
    void changeActiveValueToTrue() {
        // given
        String memberId = "memberId";
        Course course = savedBeforeCourse.get(0);
        CourseLike courseLike = CourseLike.builder()
                .course(course)
                .memberId(memberId)
                .build();
        CourseLike savedCourseLike = courseLikeRepository.saveAndFlush(courseLike);
        em.clear();
        log.info("CourseLike 저장 완료");
        // when
        savedCourseLike.changeActiveValue();
        boolean result = savedCourseLike.changeActiveValue();
        // then
        assertTrue(result);
    }
}