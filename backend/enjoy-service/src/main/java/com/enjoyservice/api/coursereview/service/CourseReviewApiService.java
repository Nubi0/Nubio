package com.enjoyservice.api.coursereview.service;

import com.enjoyservice.api.coursereview.dto.CourseReviewCreate;

public interface CourseReviewApiService {

    CourseReviewCreate.Res createCourseReview(Long courseId, CourseReviewCreate.Req request, String memberId);

    void deleteCourseReview(Long courseReviewId);
}
