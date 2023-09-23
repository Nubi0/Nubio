package com.enjoyservice.domain.coursetag.service;

import com.enjoyservice.domain.coursetag.entity.CourseTag;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseTagService {

    CourseTag save(CourseTag courseTag);

    List<Long> findAllByCourseTags(@Param("tagNames") List<String> tagNames);
}
