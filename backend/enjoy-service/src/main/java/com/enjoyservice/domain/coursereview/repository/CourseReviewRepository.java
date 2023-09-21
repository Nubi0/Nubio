package com.enjoyservice.domain.coursereview.repository;

import com.enjoyservice.domain.coursereview.entity.CourseReview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseReviewRepository extends JpaRepository<CourseReview, Long> {

    @Query("select cr from CourseReview cr where cr.course.id = :courseId")
    List<CourseReview> findCourseReviewByCourseId(@Param("courseId") Long courseId, Pageable pageable);

    @Query("select count(cr) from CourseReview cr where cr.course.id = :courseId")
    Long countCourseReviewsByCourseId(@Param("courseId") Long courseId);
}
