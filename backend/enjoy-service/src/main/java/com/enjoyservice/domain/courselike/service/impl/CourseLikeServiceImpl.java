package com.enjoyservice.domain.courselike.service.impl;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.courselike.entity.CourseLike;
import com.enjoyservice.domain.courselike.entity.type.Active;
import com.enjoyservice.domain.courselike.repository.CourseLikeRepository;
import com.enjoyservice.domain.courselike.service.CourseLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseLikeServiceImpl implements CourseLikeService {

    private final CourseLikeRepository courseLikeRepository;

    @Override
    public List<CourseLike> findAllByCourseAndActiveIsTrue(Course course) {
        return courseLikeRepository.findAllByCourseAndActive(course, Active.from(true));
    }
}
