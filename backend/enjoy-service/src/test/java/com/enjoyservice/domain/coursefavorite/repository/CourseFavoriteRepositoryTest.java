package com.enjoyservice.domain.coursefavorite.repository;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.entity.type.Content;
import com.enjoyservice.domain.course.entity.type.PublicFlag;
import com.enjoyservice.domain.course.entity.type.Title;
import com.enjoyservice.domain.course.repository.CourseRepository;
import com.enjoyservice.domain.coursefavorite.entity.CourseFavorite;
import com.enjoyservice.domain.coursefavorite.entity.type.Active;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CourseFavoriteRepositoryTest {

    @Autowired
    private CourseFavoriteRepository courseFavoriteRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EntityManager em;

    private Course savedBeforeCourse;

    @BeforeEach
    void setUp() {
        Course course = generateCourse(1, Region.DAEGU);
        savedBeforeCourse = courseRepository.saveAndFlush(course);
        em.clear();
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

    @DisplayName("코스 즐겨찾기 했을 때 여부")
    @Test
    void existsByCourseAndMemberId() {
        // given
        String memberId = "memberId";
        CourseFavorite courseFavorite = CourseFavorite.builder()
                .course(savedBeforeCourse)
                .memberId(memberId)
                .build();
        CourseFavorite savedCourseFavorite = courseFavoriteRepository.saveAndFlush(courseFavorite);
        em.clear();
        // when
        boolean result = courseFavoriteRepository.existsByCourseAndMemberIdAndActive(savedBeforeCourse, memberId, Active.from(true));
        // then
        assertTrue(result);
    }

    @DisplayName("코스 즐겨찾기 안 했을 때 여부")
    @Test
    void notExistsByCourseAndMemberId() {
        // given
        String memberId = "memberId";
        // when
        boolean result = courseFavoriteRepository.existsByCourseAndMemberIdAndActive(savedBeforeCourse, memberId, Active.from(true));
        // then
        assertFalse(result);
    }
}