package com.enjoyservice.api.course.controller;

import com.enjoyservice.api.ApiResponseEntity;
import com.enjoyservice.api.course.dto.CourseCreateReq;
import com.enjoyservice.api.course.dto.CourseDetailRes;
import com.enjoyservice.api.course.dto.CourseListRes;
import com.enjoyservice.api.course.dto.CourseTagListReq;
import com.enjoyservice.api.course.service.CourseApiService;
import com.enjoyservice.global.resolver.memberinfo.MemberInfo;
import com.enjoyservice.global.resolver.memberinfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Course API", description = "코스 api")
@RestController
@RequestMapping("v1/enjoy")
@RequiredArgsConstructor
public class CourseController {

    private final CourseApiService courseApiService;

    @Operation(summary = "코스 생성", description = "api/v1/enjoy/course\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description =  "CREATED"),
    })
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
