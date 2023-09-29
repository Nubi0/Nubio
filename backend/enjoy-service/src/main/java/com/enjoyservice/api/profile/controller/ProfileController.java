package com.enjoyservice.api.profile.controller;

import com.enjoyservice.api.ApiResponseEntity;
import com.enjoyservice.api.profile.dto.MyCourseRes;
import com.enjoyservice.api.profile.service.ProfileApiService;
import com.enjoyservice.global.resolver.memberinfo.MemberInfo;
import com.enjoyservice.global.resolver.memberinfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Profile API", description = "내 프로필 api")
@Slf4j
@RestController
@RequestMapping("v1/enjoy/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileApiService profileApiService;

    @Operation(summary = "내가 만든 코스 목록 조회", description = "enjoy/v1/enjoy/profile/course\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "OK"),
    })
    @GetMapping("/course")
    public ApiResponseEntity<MyCourseRes> getMyCourse(@MemberInfo MemberInfoDto memberInfoDto,
                                                      @PageableDefault(size = 100,
                                                              sort = "createTime",
                                                              direction = Sort.Direction.DESC) Pageable pageable) {
        MyCourseRes response = profileApiService.getMyCourses(memberInfoDto.getMemberId(), pageable);
        return ApiResponseEntity.ok(response);
    }

}
