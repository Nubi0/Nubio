package com.enjoyservice.api.courselike.controller;

import com.enjoyservice.api.ApiResponse;
import com.enjoyservice.api.courselike.dto.CourseLikeRes;
import com.enjoyservice.api.courselike.service.CourseLikeApiService;
import com.enjoyservice.global.resolver.memberinfo.MemberInfo;
import com.enjoyservice.global.resolver.memberinfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/enjoy/course")
@RequiredArgsConstructor
public class CourseLikeController {

    private final CourseLikeApiService courseLikeApiService;

    @PostMapping("/like/{courseId}")
    public ApiResponse<CourseLikeRes> courseLike(@PathVariable Long courseId, @MemberInfo MemberInfoDto memberInfoDto) {
        CourseLikeRes result = courseLikeApiService.likeCourse(memberInfoDto.getMemberId(), courseId);
        return ApiResponse.ok(result);
    }
}
