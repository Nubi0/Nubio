package com.enjoyservice.domain.coursereview.service.impl;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.coursereview.entity.CourseReview;
import com.enjoyservice.domain.coursereview.entity.type.Content;
import com.enjoyservice.domain.coursereview.entity.type.Point;
import com.enjoyservice.domain.coursereview.exception.CourseReviewNotFoundException;
import com.enjoyservice.domain.coursereview.repository.CourseReviewRepository;
import com.enjoyservice.domain.coursereview.service.CourseReviewService;
import com.enjoyservice.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseReviewServiceImpl implements CourseReviewService {

    private final CourseReviewRepository courseReviewRepository;

    @Transactional
    @Override
    public Long save(String content, int point, Course course, String memberId) {
        CourseReview courseReview = CourseReview.builder()
                .content(Content.from(content))
                .point(Point.from(point))
                .course(course)
                .memberId(memberId)
                .build();
        CourseReview savedCourseReview = courseReviewRepository.save(courseReview);
        return savedCourseReview.getId();
    }

    @Transactional
    @Override
    public void delete(Long courseReviewId) {
        CourseReview courseReview = courseReviewRepository.findById(courseReviewId)
                .orElseThrow(() -> new CourseReviewNotFoundException(ErrorCode.COURSE_REVIEW_NOT_FOUND));
        courseReviewRepository.delete(courseReview);
    }

    @Override
    public List<CourseReview> findCourseReviewsByCourseId(Long courseId, Pageable pageable) {
        return courseReviewRepository.findCourseReviewByCourseId(courseId, pageable);
    }
}
