package com.enjoyservice.api.coursefavorite.controller;

import com.enjoyservice.api.ApiResponseEntity;
import com.enjoyservice.api.coursefavorite.dto.CourseFavoriteRes;
import com.enjoyservice.api.coursefavorite.service.CourseFavoriteApiService;
import com.enjoyservice.global.resolver.memberinfo.MemberInfo;
import com.enjoyservice.global.resolver.memberinfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/enjoy/course")
@RequiredArgsConstructor
public class CourseFavoriteController {

    private final CourseFavoriteApiService courseFavoriteApiService;

    @PostMapping("/favorite/{courseId}")
    public ApiResponseEntity<CourseFavoriteRes> courseFavorite(@PathVariable("courseId") Long courseId,
                                                               @MemberInfo MemberInfoDto memberInfoDto) {
        CourseFavoriteRes response = courseFavoriteApiService.courseFavorite(courseId, memberInfoDto.getMemberId());
        return ApiResponseEntity.ok(response);
    }
}
