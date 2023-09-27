package com.enjoyservice.api.course.service;

import com.enjoyservice.api.course.dto.*;
import com.enjoyservice.domain.course.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseApiService {

    CourseCreateRes createCourse(CourseCreateReq request, String memberId);

    CourseListRes getCourseList(String region, String memberId, Pageable pageable);

    public CourseDetailRes getCourseDetail(Long courseId, String memberId);

    CourseListRes findAllByCourseTags(CourseTagListReq courseTagListReq, String memberId, Pageable pageable);

    CourseListRes findFavoriteCourseByMember(String memberId, Pageable pageable);
}
