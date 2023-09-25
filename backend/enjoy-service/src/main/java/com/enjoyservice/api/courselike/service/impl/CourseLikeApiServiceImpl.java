package com.enjoyservice.api.courselike.service.impl;

import com.enjoyservice.api.courselike.dto.CourseLikeRes;
import com.enjoyservice.api.courselike.service.CourseLikeApiService;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.service.CourseService;
import com.enjoyservice.domain.courselike.entity.CourseLike;
import com.enjoyservice.domain.courselike.service.CourseLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseLikeApiServiceImpl implements CourseLikeApiService {

    private final CourseService courseService;
    private final CourseLikeService courseLikeService;

    @Transactional
    @Override
    public CourseLikeRes likeCourse(String memberId, Long courseId) {
        Course course = courseService.findById(courseId);
        boolean result = courseService.changeCourseLikeState(courseId, memberId);
        List<CourseLike> courseLikes = courseLikeService.findAllByCourseAndActiveIsTrue(course);
        course.updateLikeCount(courseLikes.size());

        return CourseLikeRes.builder()
                .courseId(courseId)
                .likeCount(courseLikes.size())
                .likeFlag(result)
                .build();
    }
}
