package com.enjoyservice.api.course.controller;

import com.enjoyservice.api.ApiResponse;
import com.enjoyservice.api.course.dto.CourseCreateReq;
import com.enjoyservice.api.course.service.CourseApiService;
import com.enjoyservice.global.resolver.memberinfo.MemberInfo;
import com.enjoyservice.global.resolver.memberinfo.MemberInfoDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/enjoy")
@RequiredArgsConstructor
public class CourseController {

    private final CourseApiService courseApiService;

    @PostMapping("/course")
    public ApiResponse<String> createCourse(@Valid @RequestBody CourseCreateReq courseCreateReq,
                                            @MemberInfo MemberInfoDto memberInfoDto) {
        courseApiService.createCourse(courseCreateReq, memberInfoDto.getMemberId());
        return ApiResponse.created("생성 성공");
    }
}
