package com.safeservice.api.emergencymessage.service.impl;

import com.safeservice.api.emergencymessage.client.SchedulerApiClient;
import com.safeservice.api.emergencymessage.service.SchedulerApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SchedulerApiServiceImpl implements SchedulerApiService {

    private final SchedulerApiClient schedulerApiClient;

    @Override
    @Scheduled(cron = "0 0/15 * * * ?")
    public void callDataApi() {
        schedulerApiClient.requestDataApiScheduler();
    }
}
