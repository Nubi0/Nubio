package com.enjoyservice.api.coursefavorite.controller;

import com.enjoyservice.api.ApiResponseEntity;
import com.enjoyservice.api.coursefavorite.dto.CourseFavoriteRes;
import com.enjoyservice.api.coursefavorite.service.CourseFavoriteApiService;
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

@Tag(name = "CourseFavorite API", description = "코스 즐겨찾기 api")

@RestController
@RequestMapping("v1/enjoy/course")
@RequiredArgsConstructor
public class CourseFavoriteController {

    private final CourseFavoriteApiService courseFavoriteApiService;

    @Operation(summary = "코스 즐겨찾기/즐겨찾기 취소", description = "enjoy/v1/enjoy/course/favorite/{courseId}\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "OK"),
    })
    @PostMapping("/favorite/{courseId}")
    public ApiResponseEntity<CourseFavoriteRes> courseFavorite(@PathVariable("courseId") Long courseId,
                                                               @MemberInfo @Parameter(hidden = true) MemberInfoDto memberInfoDto) {
        CourseFavoriteRes response = courseFavoriteApiService.courseFavorite(courseId, memberInfoDto.getMemberId());
        return ApiResponseEntity.ok(response);
    }
}
