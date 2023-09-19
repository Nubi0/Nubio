package com.enjoyservice.domain.coursefavorite.repository;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.coursefavorite.entity.CourseFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseFavoriteRepository extends JpaRepository<CourseFavorite, Long> {

    boolean existsByCourseAndMemberId(Course course, String memberId);
}
