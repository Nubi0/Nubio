package com.enjoyservice.api.coursereview.controller;

import com.enjoyservice.api.ApiResponseEntity;
import com.enjoyservice.api.coursereview.dto.CourseReviewCreate;
import com.enjoyservice.api.coursereview.dto.CourseReviewInfosRes;
import com.enjoyservice.api.coursereview.service.CourseReviewApiService;
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

@Tag(name = "CourseReview API", description = "코스 리뷰 api")
@RestController
@RequestMapping("v1/enjoy/course")
@RequiredArgsConstructor
public class CourseReviewController {

    private final CourseReviewApiService courseReviewApiService;

    @Operation(summary = "코스 리뷰 조회", description = "enjoy/v1/enjoy/course/review/{courseId}\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description =  "CREATED"),
    })
    @GetMapping("/review/{courseId}")
    public ApiResponseEntity<CourseReviewInfosRes> getCourseReviews(@PathVariable("courseId") Long courseId,
                                                                    @PageableDefault(size = 100,
                                                                      sort = "createTime",
                                                                      direction = Sort.Direction.DESC) Pageable pageable) {
        CourseReviewInfosRes response = courseReviewApiService.getCourseReviewInfos(courseId, pageable);
        return ApiResponseEntity.ok(response);
    }

    @Operation(summary = "코스 리뷰 생성", description = "enjoy/v1/enjoy/course/review/{courseId}\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "OK"),
    })
    @PostMapping("/review/{courseId}")
    public ApiResponseEntity<CourseReviewCreate.Res> createCourseReview(@PathVariable("courseId") Long courseId,
                                                                        @Valid @RequestBody CourseReviewCreate.Req request,
                                                                        @MemberInfo @Parameter(hidden = true) MemberInfoDto memberInfoDto) {
        CourseReviewCreate.Res response = courseReviewApiService.createCourseReview(courseId, request, memberInfoDto.getMemberId());
        return ApiResponseEntity.created(response);
    }

    @Operation(summary = "코스 리뷰 삭제", description = "enjoy/v1/enjoy/course/review/{courseReviewId}\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @DeleteMapping("/review/{courseReviewId}")
    public ApiResponseEntity<String> deleteCourseReview(@PathVariable("courseReviewId") Long courseReviewId) {
        courseReviewApiService.deleteCourseReview(courseReviewId);
        return ApiResponseEntity.ok("삭제 완료");
    }

}
