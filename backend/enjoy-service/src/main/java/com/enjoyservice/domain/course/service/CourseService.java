package com.enjoyservice.domain.course.service;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;

import java.util.List;

public interface CourseService {

    Course save(Course course);

    List<Course> findAllByRegion(Region region);
}
