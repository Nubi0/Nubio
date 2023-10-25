package com.safeservice.api.chat.service.impl;

import com.safeservice.api.chat.dto.ChatClientDto;
import com.safeservice.api.chat.service.ChatClientService;
import com.safeservice.api.emergencymessage.client.KakaoMapClient;
import com.safeservice.api.emergencymessage.dto.client.ClientDto;
import com.safeservice.domain.emergencymessage.entity.EmergencyMessage;
import com.safeservice.domain.emergencymessage.service.EmergencyMessageService;
import com.safeservice.domain.report.entity.Report;
import com.safeservice.domain.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatClientServiceimpl implements ChatClientService {

    private final KakaoMapClient kakaoMapClient;
    private final ReportService reportService;
    private final EmergencyMessageService emergencyMessageService;

    @Value("${cloud.openfeign.client.config.feignName.appKey}")
    private String appKey;


    @Override
    public ChatClientDto getRegion(double longitude, double latitude) {
        ClientDto clientDto = kakaoMapClient.requestKakaoToken(appKey, longitude, latitude);

        String region = clientDto.getDocuments().get(0).getRegion_1depth_name() + " " +
                clientDto.getDocuments().get(0).getRegion_2depth_name();
        Report report = reportService.searchReport(longitude, latitude, region);

        List<EmergencyMessage> emergencyMessages = emergencyMessageService.searchEM(
                clientDto.getDocuments().get(0).getRegion_1depth_name());
        return ChatClientDto.of(clientDto,
                emergencyMessages.isEmpty() ? null : emergencyMessages.get(0),
                report == null ? null : report);
    }
}
