package com.enjoyservice.api.recommendation.client;

import com.enjoyservice.api.recommendation.dto.kakao.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json", name = "kakaoMapClient")
public interface KakaoMapClient {

    @GetMapping(consumes = "application/json")
    ClientDto getRegion(@RequestHeader("Authorization") String auth,
                                @RequestParam("x") double x, @RequestParam("y") double y);
}
