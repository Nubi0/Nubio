package com.enjoyservice.api.coursereview.service.impl;

import com.enjoyservice.api.coursereview.dto.CourseReviewCreate;
import com.enjoyservice.api.coursereview.service.CourseReviewApiService;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.service.CourseService;
import com.enjoyservice.domain.coursereview.service.CourseReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseReviewApiServiceImpl implements CourseReviewApiService {

    private final CourseService courseService;
    private final CourseReviewService courseReviewService;

    @Transactional
    @Override
    public CourseReviewCreate.Res createCourseReview(Long courseId, CourseReviewCreate.Req request, String memberId) {
        Course course = courseService.findById(courseId);
        Long savedCourseReviewId = courseReviewService.save(request.getContent(), request.getPoint(), course, memberId);
        return CourseReviewCreate.Res.builder()
                .courseReviewId(savedCourseReviewId)
                .build();
    }
}
