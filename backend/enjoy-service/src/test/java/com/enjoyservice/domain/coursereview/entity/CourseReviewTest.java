package com.enjoyservice.domain.coursereview.entity;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.entity.type.Content;
import com.enjoyservice.domain.course.entity.type.PublicFlag;
import com.enjoyservice.domain.course.entity.type.Title;
import com.enjoyservice.domain.course.repository.CourseRepository;
import com.enjoyservice.domain.coursereview.entity.type.Point;
import com.enjoyservice.domain.coursereview.repository.CourseReviewRepository;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.entity.constant.GroupCode;
import com.enjoyservice.domain.place.entity.constant.GroupName;
import com.enjoyservice.domain.place.entity.type.*;
import com.enjoyservice.domain.place.repository.PlaceRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class CourseReviewTest {

    @Autowired
    private CourseReviewRepository courseReviewRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EntityManager em;

    private Course beforeSavedCourse;

    @BeforeEach
    void setUp() {
        // Course 1개 저장
        Course course = generateCourse(1, Region.DAEGU);
        beforeSavedCourse = courseRepository.saveAndFlush(course);
        em.clear();
        log.info("================================setUp===============================");
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

    @DisplayName("CourseReview soft delete 확인")
    @Test
    void softDelete() {
        // given
        String content = "content";
        int point = 4;
        String memberId = "memberId";
        Course course = beforeSavedCourse;
        CourseReview courseReview = CourseReview.builder()
                .content(com.enjoyservice.domain.coursereview.entity.type.Content.from(content))
                .point(Point.from(point))
                .course(course)
                .memberId(memberId)
                .build();
        CourseReview savedCourseReview = courseReviewRepository.saveAndFlush(courseReview);
        em.clear();
        // when
        courseReviewRepository.delete(savedCourseReview);
        Optional<CourseReview> result = courseReviewRepository.findById(savedCourseReview.getId());
        // then
        assertThat(result).isEmpty();
    }
}