package com.enjoyservice.api.profile.service.impl;

import com.enjoyservice.api.profile.dto.MyCourseRes;
import com.enjoyservice.api.profile.service.ProfileApiService;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.service.CourseService;
import com.enjoyservice.mapper.profile.MyCourseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileApiServiceImpl implements ProfileApiService {

    private final CourseService courseService;

    @Override
    public MyCourseRes getMyCourses(String memberId, Pageable pageable) {
        // 내가 만든 코스 + 코스에 연관된 장소 + 대표이미지 + 코스 태그 조회
        Page<Course> pagedCourses = courseService.findMyCourses(memberId, pageable);
        // 페이징 정보

        return MyCourseMapper.PagedCourseToMyCourseRes(pagedCourses);
    }
}
