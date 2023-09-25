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
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(summary = "코스 생성", description = "enjoy/v1/enjoy/course\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description =  "CREATED"),
    })
    @PostMapping("/course")
    public ApiResponseEntity<String> createCourse(@Valid @RequestBody CourseCreateReq courseCreateReq,
                                                  @MemberInfo MemberInfoDto memberInfoDto) {
        courseApiService.createCourse(courseCreateReq, memberInfoDto.getMemberId());
        return ApiResponseEntity.created("생성 성공");
    }

    @Operation(summary = "코스 목록 조회(지역별)", description = "enjoy/v1/enjoy/course\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "OK"),
    })
    @GetMapping("/course")
    public ApiResponseEntity<CourseListRes> getCourseList(@RequestParam("region") String region,
                                                          @MemberInfo MemberInfoDto memberInfoDto,
                                                          @PageableDefault(size = 100,
                                                            sort = "createTime",
                                                            direction = Sort.Direction.DESC) Pageable pageable) {
        CourseListRes response = courseApiService.getCourseList(region, memberInfoDto.getMemberId(), pageable);
        return ApiResponseEntity.ok(response);
    }

    @Operation(summary = "코스 상세 조회", description = "enjoy/v1/enjoy/course/{courseId}\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "OK"),
    })
    @GetMapping("/course/{courseId}")
    public ApiResponseEntity<CourseDetailRes> getCourseDetail(@PathVariable Long courseId,
                                                              @MemberInfo @Parameter(hidden = true) MemberInfoDto memberInfoDto) {
        CourseDetailRes response = courseApiService.getCourseDetail(courseId, memberInfoDto.getMemberId());
        return ApiResponseEntity.ok(response);
    }

    @Operation(summary = "태그 리스트로 코스 목록 조회", description = "enjoy/v1/enjoy/course/filter\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "OK"),
    })
    @PostMapping("/course/filter")
    public ApiResponseEntity<CourseListRes> getCourseDetailByCourseTags(@RequestBody CourseTagListReq courseTagListReq,
                                                                        @MemberInfo @Parameter(hidden = true) MemberInfoDto memberInfoDto,
                                                                        @PageableDefault(size = 100,
                                                            sort = "createTime",
                                                            direction = Sort.Direction.DESC) Pageable pageable) {
        CourseListRes response = courseApiService.findAllByCourseTags(courseTagListReq, memberInfoDto.getMemberId(), pageable);
        return ApiResponseEntity.ok(response);
    }
}
