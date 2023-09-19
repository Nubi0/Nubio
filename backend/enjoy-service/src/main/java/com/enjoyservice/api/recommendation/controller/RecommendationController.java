package com.enjoyservice.api.recommendation.controller;

import com.enjoyservice.api.ApiResponse;
import com.enjoyservice.api.recommendation.client.FastApiClient;
import com.enjoyservice.api.recommendation.dto.FastRecoReq;
import com.enjoyservice.api.recommendation.dto.FastRecoRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/enjoy/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final FastApiClient fastApiClient;

    @GetMapping("/test")
    public ApiResponse<FastRecoRes> test() {
        List<String> test = new ArrayList<>();
        test.add("당구");
        test.add("꽃구경");
        FastRecoReq build = FastRecoReq.builder().words(test).build();
        FastRecoRes reco = fastApiClient.getReco(build);
        return ApiResponse.ok(reco);
    }
}
