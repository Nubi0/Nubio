package com.enjoyservice.api.course.service;

import com.enjoyservice.api.course.dto.CourseCreateReq;
import com.enjoyservice.api.course.dto.CourseListRes;
import org.springframework.data.domain.Pageable;

public interface CourseApiService {

    void createCourse(CourseCreateReq request, String memberId);

    CourseListRes getCourseList(String region, String memberId, Pageable pageable);
}
