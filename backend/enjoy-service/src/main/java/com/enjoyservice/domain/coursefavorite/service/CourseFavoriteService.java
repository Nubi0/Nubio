package com.enjoyservice.domain.coursefavorite.service;

import com.enjoyservice.domain.course.entity.Course;

public interface CourseFavoriteService {

    boolean existsByCourseAndMemberId(Course course, String memberId);

    boolean changeCourseFavoriteState(String memberId, Course course);
}
