package com.enjoyservice.api.recommendation.service.impl;

import com.enjoyservice.api.recommendation.client.SchedulerClient;
import com.enjoyservice.api.recommendation.service.SchedulerApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SchedulerApiServiceImpl implements SchedulerApiService {

    private final SchedulerClient schedulerClient;

    @Override
//    @Scheduled(cron = "0 0 3 * * ?") TODO : 나중에 최종 배포할 때 주석 풀기
    public void makeModel() {
        schedulerClient.requestMakeModelScheduler();
    }
}
