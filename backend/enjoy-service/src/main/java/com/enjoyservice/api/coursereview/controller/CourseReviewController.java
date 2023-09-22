package com.enjoyservice.api.coursereview.controller;

import com.enjoyservice.api.ApiResponseEntity;
import com.enjoyservice.api.coursereview.dto.CourseReviewCreate;
import com.enjoyservice.api.coursereview.dto.CourseReviewInfosRes;
import com.enjoyservice.api.coursereview.service.CourseReviewApiService;
import com.enjoyservice.global.resolver.memberinfo.MemberInfo;
import com.enjoyservice.global.resolver.memberinfo.MemberInfoDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/enjoy/course")
@RequiredArgsConstructor
public class CourseReviewController {

    private final CourseReviewApiService courseReviewApiService;

    @GetMapping("/review/{courseId}")
    public ApiResponseEntity<CourseReviewInfosRes> getCourseReviews(@PathVariable("courseId") Long courseId,
                                                                    @PageableDefault(size = 100,
                                                                      sort = "createTime",
                                                                      direction = Sort.Direction.DESC) Pageable pageable) {
        CourseReviewInfosRes response = courseReviewApiService.getCourseReviewInfos(courseId, pageable);
        return ApiResponseEntity.ok(response);
    }

    @PostMapping("/review/{courseId}")
    public ApiResponseEntity<CourseReviewCreate.Res> createCourseReview(@PathVariable("courseId") Long courseId,
                                                                        @Valid @RequestBody CourseReviewCreate.Req request,
                                                                        @MemberInfo MemberInfoDto memberInfoDto) {
        CourseReviewCreate.Res response = courseReviewApiService.createCourseReview(courseId, request, memberInfoDto.getMemberId());
        return ApiResponseEntity.created(response);
    }

    @DeleteMapping("/review/{courseReviewId}")
    public ApiResponseEntity<String> deleteCourseReview(@PathVariable("courseReviewId") Long courseReviewId) {
        courseReviewApiService.deleteCourseReview(courseReviewId);
        return ApiResponseEntity.ok("삭제 완료");
    }

}
