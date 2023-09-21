package com.enjoyservice.domain.coursereview.service;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.coursereview.entity.CourseReview;

public interface CourseReviewService {

    Long save(String content, int point, Course course, String memberId);
}
