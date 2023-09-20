package com.enjoyservice.api.course.controller;

import com.enjoyservice.api.ApiResponse;
import com.enjoyservice.api.course.dto.CourseCreateReq;
import com.enjoyservice.api.course.dto.CourseDetailRes;
import com.enjoyservice.api.course.dto.CourseListRes;
import com.enjoyservice.api.course.service.CourseApiService;
import com.enjoyservice.global.resolver.memberinfo.MemberInfo;
import com.enjoyservice.global.resolver.memberinfo.MemberInfoDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/course")
    public ApiResponse<CourseListRes> getCourseList(@RequestParam("region") String region,
                                                    @MemberInfo MemberInfoDto memberInfoDto,
                                                    @PageableDefault(size = 100,
                                                            sort = "createTime",
                                                            direction = Sort.Direction.DESC) Pageable pageable) {
        CourseListRes response = courseApiService.getCourseList(region, memberInfoDto.getMemberId(), pageable);
        return ApiResponse.ok(response);
    }

    @GetMapping("/course/{courseId}")
    public ApiResponse<CourseDetailRes> getCourseDetail(@PathVariable Long courseId, @MemberInfo MemberInfoDto memberInfoDto) {
        CourseDetailRes response = courseApiService.getCourseDetail(courseId, memberInfoDto.getMemberId());
        return ApiResponse.ok(response);
    }
}
