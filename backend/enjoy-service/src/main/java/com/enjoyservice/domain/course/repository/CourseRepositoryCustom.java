package com.enjoyservice.domain.course.repository;


import com.enjoyservice.domain.course.dto.CourseDto;
import com.enjoyservice.domain.course.entity.constant.Region;

import java.util.List;

public interface CourseRepositoryCustom {
    List<CourseDto> findCourseForModel(Region region);
}
