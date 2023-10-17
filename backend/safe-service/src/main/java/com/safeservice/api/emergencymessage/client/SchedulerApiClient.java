package com.safeservice.api.emergencymessage.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "https://nubi0.com/safe/v1/safe", name = "schedulerClient")
public interface SchedulerApiClient {

    @GetMapping(value = "/emergency/call")
    void requestDataApiScheduler();
}
