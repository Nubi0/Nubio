package com.enjoyservice.api.recommendation.controller;

import com.enjoyservice.api.ApiResponseEntity;
import com.enjoyservice.api.recommendation.client.FastApiClient;
import com.enjoyservice.api.recommendation.dto.RecommendationReq;
import com.enjoyservice.api.recommendation.dto.RecommendationRes;
import com.enjoyservice.api.recommendation.dto.fastapi.FastCreateReq;
import com.enjoyservice.api.recommendation.dto.fastapi.FastRecoRes;
import com.enjoyservice.api.recommendation.service.RecommendationApiService;
import com.enjoyservice.global.resolver.memberinfo.MemberInfo;
import com.enjoyservice.global.resolver.memberinfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "recommendation API", description = "코스 추천 api")
@RestController
@RequestMapping("/v1/enjoy")
@RequiredArgsConstructor
public class RecommendationController {

    private final FastApiClient fastApiClient;
    private final RecommendationApiService recommendationApiService;

    @Operation(summary = "코스 추천 조회", description = "enjoy/v1/enjoy\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "OK"),
    })
    @PostMapping("")
    public ApiResponseEntity<RecommendationRes> getReco(@MemberInfo MemberInfoDto memberInfoDto,
                                                  @RequestBody RecommendationReq recommendationReq) {
        RecommendationRes recoCourses = recommendationApiService.getCourses(memberInfoDto.getMemberId(), recommendationReq);
        return ApiResponseEntity.ok(recoCourses);
    }

    @GetMapping("/create")
    public ApiResponseEntity<String> create() {
//        String[] regions = {"DAEGU","GYEONGBUK","SEOUL","DAEJEON","BUSAN","GWANGJU"};
        String[] regions = {"DAEGU", "GYEONGBUK"};
        for (String region : regions) {
            recommendationApiService.saveModel(region);
            fastApiClient.createModel(FastCreateReq.from(region));
        }
        return ApiResponseEntity.ok("모델 생성 완료");
    }


}
