package com.enjoyservice.domain.coursetag.service.impl;

import com.enjoyservice.domain.coursetag.entity.CourseTag;
import com.enjoyservice.domain.coursetag.repository.CourseTagRepository;
import com.enjoyservice.domain.coursetag.service.CourseTagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseTagServiceImpl implements CourseTagService {

    private final CourseTagRepository courseTagRepository;

    @Override
    public CourseTag save(CourseTag courseTag) {
        return courseTagRepository.save(courseTag);
    }
}
