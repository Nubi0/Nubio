package com.enjoyservice.api.profile.service;

import com.enjoyservice.api.profile.dto.MyCourseRes;
import org.springframework.data.domain.Pageable;

public interface ProfileApiService {

    MyCourseRes getMyCourses(String memberId, Pageable pageable);
}
