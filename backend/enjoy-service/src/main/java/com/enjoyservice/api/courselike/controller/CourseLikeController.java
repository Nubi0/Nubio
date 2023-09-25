package com.enjoyservice.api.courselike.controller;

import com.enjoyservice.api.ApiResponseEntity;
import com.enjoyservice.api.courselike.dto.CourseLikeRes;
import com.enjoyservice.api.courselike.service.CourseLikeApiService;
import com.enjoyservice.global.resolver.memberinfo.MemberInfo;
import com.enjoyservice.global.resolver.memberinfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CourseLike API", description = "코스 좋아요 api")

@RestController
@RequestMapping("v1/enjoy/course")
@RequiredArgsConstructor
public class CourseLikeController {

    private final CourseLikeApiService courseLikeApiService;

    @Operation(summary = "코스 좋아요/좋아요 취 ", description = "enjoy/v1/enjoy/course/like/{courseId}\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description =  "CREATED"),
    })
    @PostMapping("/like/{courseId}")
    public ApiResponseEntity<CourseLikeRes> courseLike(@PathVariable Long courseId,
                                                       @MemberInfo @Parameter(hidden = true) MemberInfoDto memberInfoDto) {
        CourseLikeRes result = courseLikeApiService.likeCourse(memberInfoDto.getMemberId(), courseId);
        return ApiResponseEntity.ok(result);
    }
}
