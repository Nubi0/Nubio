package com.enjoyservice.domain.courselike.service;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.courselike.entity.CourseLike;

import java.util.List;

public interface CourseLikeService {

    List<CourseLike> findAllByCourseAndActiveIsTrue(Course course);
}
