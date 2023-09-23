package com.enjoyservice.api.recommendation.controller;

import com.enjoyservice.api.ApiResponseEntity;
import com.enjoyservice.api.recommendation.client.FastApiClient;
import com.enjoyservice.api.recommendation.dto.RecommendationReq;
import com.enjoyservice.api.recommendation.dto.fastapi.FastCreateReq;
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
    public ApiResponseEntity<FastRecoRes> getReco(@MemberInfo MemberInfoDto memberInfoDto,
                                                  @RequestBody RecommendationReq recommendationReq) {
        FastRecoRes recoCourses = recommendationApiService.getCourses(memberInfoDto.getMemberId(), recommendationReq);
        return ApiResponseEntity.ok(recoCourses);
    }

    @GetMapping("/create")
    public ApiResponseEntity<String> create() {
//        String[] regions = {"DAEGU","GYEONGBUK","SEOUL","DAEJEON","BUSAN","GWANGJU"};
        String[] regions = {"DAEGU","SEOUL"};
        for (String region : regions) {
            recommendationApiService.saveModel(region);
            fastApiClient.createModel(FastCreateReq.from(region));
        }
        return ApiResponseEntity.ok("모델 생성 완료");
    }


}
