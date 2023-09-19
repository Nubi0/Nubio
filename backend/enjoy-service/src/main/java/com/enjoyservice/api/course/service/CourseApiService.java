package com.enjoyservice.api.course.service;

import com.enjoyservice.api.course.dto.CourseCreateReq;

public interface CourseApiService {

    void createCourse(CourseCreateReq request, String memberId);
}
