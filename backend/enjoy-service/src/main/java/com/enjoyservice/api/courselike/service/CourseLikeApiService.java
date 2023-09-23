package com.enjoyservice.api.courselike.service;

import com.enjoyservice.api.courselike.dto.CourseLikeRes;

public interface CourseLikeApiService {

    public CourseLikeRes likeCourse(String memberId, Long courseId);
}
