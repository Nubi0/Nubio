package com.enjoyservice.domain.course.repository;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByRegion(Region region, Pageable pageable);
}
