package com.enjoyservice.api.profile.service.impl;

import com.enjoyservice.api.profile.dto.MyCourseRes;
import com.enjoyservice.api.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    @Override
    public MyCourseRes getMyCourses(String memberId) {
        // 내가 만든 코스 + 코스에 연관된 장소 + 대표이미지 + 코스 태그 조회

        // 페이징 정보

        return null;
    }
}
