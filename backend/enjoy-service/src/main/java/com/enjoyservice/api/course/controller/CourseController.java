package com.enjoyservice.api.course.controller;

import com.enjoyservice.api.ApiResponseEntity;
import com.enjoyservice.api.course.dto.CourseCreateReq;
import com.enjoyservice.api.course.dto.CourseDetailRes;
import com.enjoyservice.api.course.dto.CourseListRes;
import com.enjoyservice.api.course.dto.CourseTagListReq;
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
    public ApiResponseEntity<String> createCourse(@Valid @RequestBody CourseCreateReq courseCreateReq,
                                                  @MemberInfo MemberInfoDto memberInfoDto) {
        courseApiService.createCourse(courseCreateReq, memberInfoDto.getMemberId());
        return ApiResponseEntity.created("생성 성공");
    }

    @GetMapping("/course")
    public ApiResponseEntity<CourseListRes> getCourseList(@RequestParam("region") String region,
                                                          @MemberInfo MemberInfoDto memberInfoDto,
                                                          @PageableDefault(size = 100,
                                                            sort = "createTime",
                                                            direction = Sort.Direction.DESC) Pageable pageable) {
        CourseListRes response = courseApiService.getCourseList(region, memberInfoDto.getMemberId(), pageable);
        return ApiResponseEntity.ok(response);
    }

    @GetMapping("/course/{courseId}")
    public ApiResponseEntity<CourseDetailRes> getCourseDetail(@PathVariable Long courseId, @MemberInfo MemberInfoDto memberInfoDto) {
        CourseDetailRes response = courseApiService.getCourseDetail(courseId, memberInfoDto.getMemberId());
        return ApiResponseEntity.ok(response);
    }

    @PostMapping("/course/filter")
    public ApiResponseEntity<CourseListRes> getCourseDetailByCourseTags(@RequestBody CourseTagListReq courseTagListReq,
                                                                        @MemberInfo MemberInfoDto memberInfoDto,
                                                                        @PageableDefault(size = 100,
                                                            sort = "createTime",
                                                            direction = Sort.Direction.DESC) Pageable pageable) {
        CourseListRes response = courseApiService.findAllByCourseTags(courseTagListReq, memberInfoDto.getMemberId(), pageable);
        return ApiResponseEntity.ok(response);
    }
}
