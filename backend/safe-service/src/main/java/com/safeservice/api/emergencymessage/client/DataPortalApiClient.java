package com.safeservice.api.emergencymessage.client;

import com.safeservice.api.emergencymessage.dto.client.DataApiDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "https://apis.data.go.kr/1741000/DisasterMsg3", name = "DataPortalClient")
public interface DataPortalApiClient {
    @GetMapping(value = "/getDisasterMsg1List?serviceKey=ZBlbsX27bnVykE6yaeVoKS00GpfGJkqMm%2BEVd8dDP9D8w2rK8bfe1PzwM7z%2B3XskXJM2eYo4PYVC6D374zGtjQ%3D%3D&pageNo=1&numOfRows=2&type=json",
            produces = "application/json")
    DataApiDto requestDataApi();
}
