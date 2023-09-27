package com.enjoyservice.api.profile.service;

import com.enjoyservice.api.profile.dto.MyCourseRes;

public interface ProfileService {

    MyCourseRes getMyCourses(String memberId);
}
