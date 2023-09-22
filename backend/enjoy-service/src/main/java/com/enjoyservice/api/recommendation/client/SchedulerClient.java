package com.enjoyservice.api.recommendation.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "http://localhost:8081/v1", name = "schedulerClient")
public interface SchedulerClient {

    @GetMapping(value = "/enjoy/create")
    void requestMakeModelScheduler();
}
