package com.enjoyservice.api.recommendation.client;

import com.enjoyservice.api.recommendation.dto.FastRecoReq;
import com.enjoyservice.api.recommendation.dto.FastRecoRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://127.0.0.1:8000/get-list", name = "FastApi")
public interface FastApiClient {

    @PostMapping(consumes = "application/json")
    FastRecoRes getReco(@RequestBody FastRecoReq words);

}
