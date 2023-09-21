package com.enjoyservice.domain.coursereview.repository;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.entity.type.PublicFlag;
import com.enjoyservice.domain.course.entity.type.Title;
import com.enjoyservice.domain.course.repository.CourseRepository;
import com.enjoyservice.domain.coursereview.entity.CourseReview;
import com.enjoyservice.domain.coursereview.entity.type.Content;
import com.enjoyservice.domain.coursereview.entity.type.Point;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CourseReviewRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseReviewRepository courseReviewRepository;
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
    
    @DisplayName("courseId로 CourseReview 목록 조회하기")
    @Test
    void findCourseReviewByCourseId() {
        // given
        int reviewCnt = 5;
        Course course = beforeSavedCourse;
        Long courseId = course.getId();
        for(int i = 1; i <= reviewCnt; i++) {
            String memberId = "memberId";
            String content = "content" + i;
            int point = 4;
            CourseReview courseReview = CourseReview.builder()
                    .content(Content.from(content))
                    .point(Point.from(point))
                    .memberId(memberId)
                    .course(course)
                    .build();
            CourseReview savedCourseReview = courseReviewRepository.saveAndFlush(courseReview);
        }
        em.clear();
        // when
        List<CourseReview> result = courseReviewRepository.findCourseReviewByCourseId(courseId);
        // then
        assertThat(result).hasSize(reviewCnt)
                .extracting("content.value", "course.id")
                .containsExactlyInAnyOrder(
                        tuple("content1", courseId),
                        tuple("content2", courseId),
                        tuple("content3", courseId),
                        tuple("content4", courseId),
                        tuple("content5", courseId)
                );
    }
}