package com.enjoyservice.domain.coursereview.repository;

import com.enjoyservice.domain.coursereview.entity.CourseReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseReviewRepository extends JpaRepository<CourseReview, Long> {
}
