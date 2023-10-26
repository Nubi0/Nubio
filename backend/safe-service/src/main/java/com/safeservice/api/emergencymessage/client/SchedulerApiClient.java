package com.safeservice.api.emergencymessage.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "http://ec2-13-124-94-38.ap-northeast-2.compute.amazonaws.com:8082/v1/safe", name = "schedulerClient")
public interface SchedulerApiClient {

    @GetMapping(value = "/emergency/call")
    void requestDataApiScheduler();
}
