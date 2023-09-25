package com.enjoyservice.api.coursefavorite.service.impl;

import com.enjoyservice.api.coursefavorite.dto.CourseFavoriteRes;
import com.enjoyservice.api.coursefavorite.service.CourseFavoriteApiService;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.service.CourseService;
import com.enjoyservice.domain.coursefavorite.service.CourseFavoriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseFavoriteApiServiceImpl implements CourseFavoriteApiService {

    private final CourseService courseService;
    private final CourseFavoriteService courseFavoriteService;

    @Transactional
    @Override
    public CourseFavoriteRes courseFavorite(Long courseId, String memberId) {
        Course course = courseService.findById(courseId);
        boolean result = courseFavoriteService.changeCourseFavoriteState(memberId, course);
        return CourseFavoriteRes.builder()
                .courseId(courseId)
                .courseFavoriteFlag(result)
                .build();
    }
}
