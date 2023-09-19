package com.enjoyservice.domain.course.service;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {

    Course save(Course course);

    List<Course> findAllByRegionFetchPlace(Region region, Pageable pageable);

    Long countAllByRegion(Region region);
}
