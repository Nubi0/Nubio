package com.enjoyservice.api.coursereview.service;

import com.enjoyservice.api.coursereview.dto.CourseReviewCreate;
import com.enjoyservice.api.coursereview.dto.CourseReviewInfosRes;
import org.springframework.data.domain.Pageable;

public interface CourseReviewApiService {

    CourseReviewCreate.Res createCourseReview(Long courseId, CourseReviewCreate.Req request, String memberId);

    void deleteCourseReview(Long courseReviewId);

    CourseReviewInfosRes getCourseReviewInfos(Long courseId, Pageable pageable);
}
