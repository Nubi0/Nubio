package com.enjoyservice.api.recommendation.controller;

import com.enjoyservice.api.ApiResponse;
import com.enjoyservice.api.recommendation.client.FastApiClient;
import com.enjoyservice.api.recommendation.dto.RecommendationReq;
import com.enjoyservice.api.recommendation.dto.fastapi.FastRecoRes;
import com.enjoyservice.api.recommendation.service.RecommendationApiService;
import com.enjoyservice.global.resolver.memberinfo.MemberInfo;
import com.enjoyservice.global.resolver.memberinfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/enjoy")
@RequiredArgsConstructor
public class RecommendationController {

    private final FastApiClient fastApiClient;
    private final RecommendationApiService recommendationApiService;

    @PostMapping("")
    public ApiResponse<FastRecoRes> getReco(@MemberInfo MemberInfoDto memberInfoDto,
                                            @RequestBody RecommendationReq recommendationReq) {
        FastRecoRes recoCourses = recommendationApiService.getCourses(memberInfoDto.getMemberId(), recommendationReq);
        return ApiResponse.ok(recoCourses);
    }

    @GetMapping("/create")
    public ApiResponse<String> create() {
        recommendationApiService.saveModel();
        return ApiResponse.ok("모델 생성 완료");
    }


}
