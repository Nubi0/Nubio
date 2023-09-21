package com.enjoyservice.domain.coursereview.service;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.coursereview.entity.CourseReview;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseReviewService {

    Long save(String content, int point, Course course, String memberId);

    void delete(Long courseReviewId);

    List<CourseReview> findCourseReviewsByCourseId(Long courseId, Pageable pageable);

    Long countCourseReviewsByCourseId(Long courseId);
}
