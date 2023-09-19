package com.enjoyservice.domain.coursefavorite.service.impl;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.coursefavorite.repository.CourseFavoriteRepository;
import com.enjoyservice.domain.coursefavorite.service.CourseFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseFavoriteServiceImpl implements CourseFavoriteService {

    private final CourseFavoriteRepository courseFavoriteRepository;

    @Override
    public boolean existsByCourseAndMemberId(Course course, String memberId) {
        return courseFavoriteRepository.existsByCourseAndMemberId(course, memberId);
    }
}
