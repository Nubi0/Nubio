package com.enjoyservice.domain.coursereview.service.impl;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.entity.type.PublicFlag;
import com.enjoyservice.domain.course.entity.type.Title;
import com.enjoyservice.domain.course.repository.CourseRepository;
import com.enjoyservice.domain.coursereview.entity.CourseReview;
import com.enjoyservice.domain.coursereview.entity.type.Content;
import com.enjoyservice.domain.coursereview.entity.type.Point;
import com.enjoyservice.domain.coursereview.repository.CourseReviewRepository;
import com.enjoyservice.domain.coursereview.service.CourseReviewService;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class CourseReviewServiceImplTest {

    @Autowired
    private CourseReviewService courseReviewService;
    @Autowired
    private CourseReviewRepository courseReviewRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EntityManager em;

    private Course beforeSavedCourse;

    @BeforeEach
    void setUp() {
        Course course = generateCourse(1, Region.DAEGU);
        beforeSavedCourse = courseRepository.save(course);
        em.clear();
    }

    private Course generateCourse(int index, Region region) {
        return Course.builder()
                .title(Title.from("courseTitle" + index))
                .content(com.enjoyservice.domain.course.entity.type.Content.from("courseContent" + index))
                .region(region)
                .publicFlag(PublicFlag.from(true))
                .memberId("memberId" + index)
                .build();
    }

    @DisplayName("CourseReview 저장 기능")
    @Test
    void save() {
        // given
        String content = "content";
        int point = 4;
        Course course = beforeSavedCourse;
        String memberId = "memberId";

        CourseReview courseReview = CourseReview.builder()
                .content(Content.from(content))
                .point(Point.from(point))
                .course(course)
                .memberId(memberId)
                .build();
        // when
        Long result = courseReviewService.save(content, point, course, memberId);

        // then
        assertThat(result).isNotNull();
    }

    @DisplayName("CourseReview 삭제시 soft delete")
    @Test
    void delete() {
        // given
        String content = "content";
        int point = 4;
        Course course = beforeSavedCourse;
        String memberId = "memberId";

        CourseReview courseReview = CourseReview.builder()
                .content(Content.from(content))
                .point(Point.from(point))
                .course(course)
                .memberId(memberId)
                .build();
        CourseReview savedCourseReview = courseReviewRepository.saveAndFlush(courseReview);
        em.clear();
        // when
        courseReviewService.delete(savedCourseReview.getId());
        em.flush();
        em.clear();
        Optional<CourseReview> result = courseReviewRepository.findById(savedCourseReview.getId());
        // then
        assertThat(result).isEmpty();
    }
}