package com.enjoyservice.api.coursefavorite.service;

import com.enjoyservice.api.coursefavorite.dto.CourseFavoriteRes;

public interface CourseFavoriteApiService {

    CourseFavoriteRes courseFavorite(Long courseId, String memberId);
}
