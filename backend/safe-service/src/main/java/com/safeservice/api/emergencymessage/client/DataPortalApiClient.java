package com.safeservice.api.emergencymessage.client;

import com.safeservice.api.emergencymessage.dto.client.DataApiDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://apis.data.go.kr/1741000/DisasterMsg3/getDisasterMsg1List", name = "DataPortalClient")
public interface DataPortalApiClient {
    String serviceKey = "ZBlbsX27bnVykE6yaeVoKS00GpfGJkqMm%2BEVd8dDP9D8w2rK8bfe1PzwM7z%2B3XskXJM2eYo4PYVC6D374zGtjQ%3D%3D";
    @GetMapping(consumes = "application/json")
    String requestDataApi(@RequestParam("serviceKey") String serviceKey,
                          @RequestParam("pageNo") String pageNo,
                          @RequestParam("numOfRows") String numOfRows,
                          @RequestParam("type") String type);
}
