package com.enjoyservice.api.recommendation.client;

import com.enjoyservice.api.recommendation.dto.fastapi.FastCreateReq;
import com.enjoyservice.api.recommendation.dto.fastapi.FastRecoReq;
import com.enjoyservice.api.recommendation.dto.fastapi.FastRecoRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://127.0.0.1:8000", name = "FastApi")
public interface FastApiClient {

    @PostMapping(value = "/get-list",consumes = "application/json")
    FastRecoRes getReco(@RequestBody FastRecoReq fastRecoReq);

    @PostMapping(value = "/get-all",consumes = "application/json")
    void createModel(@RequestBody FastCreateReq createRes);
}
