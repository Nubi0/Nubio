package com.enjoyservice.api.coursereview.controller;

import com.enjoyservice.api.ApiResponse;
import com.enjoyservice.api.coursereview.dto.CourseReviewCreate;
import com.enjoyservice.api.coursereview.service.CourseReviewApiService;
import com.enjoyservice.global.resolver.memberinfo.MemberInfo;
import com.enjoyservice.global.resolver.memberinfo.MemberInfoDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/enjoy/course")
@RequiredArgsConstructor
public class CourseReviewController {

    private final CourseReviewApiService courseReviewApiService;

    @PostMapping("/review/{courseId}")
    public ApiResponse<CourseReviewCreate.Res> createCourseReview(@PathVariable("courseId") Long courseId,
                                                  @Valid @RequestBody CourseReviewCreate.Req request,
                                                  @MemberInfo MemberInfoDto memberInfoDto) {
        CourseReviewCreate.Res response = courseReviewApiService.createCourseReview(courseId, request, memberInfoDto.getMemberId());
        return ApiResponse.created(response);
    }

}
