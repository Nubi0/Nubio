package com.enjoyservice.domain.course.repository;

import com.enjoyservice.domain.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
