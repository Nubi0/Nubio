package com.safeservice.api.emergencymessage.service;

import com.safeservice.api.emergencymessage.client.KakaoMapClient;
import com.safeservice.api.emergencymessage.dto.*;
import com.safeservice.api.emergencymessage.dto.client.ClientDto;
import com.safeservice.domain.emergencymessage.entity.EmergencyMessage;
import com.safeservice.domain.emergencymessage.service.EmergencyMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class EmergencyMessageInfoService {

    private final EmergencyMessageService emergencyMessageService;
    private final KakaoMapClient kakaoMapClient;

    @Value("${cloud.openfeign.client.config.feignName.appKey}")
    private String appKey;

    @Transactional
    public void createEmergencyMessage(EMReq emReq) {
        emReq.getData().forEach(emRequestDto ->
                emergencyMessageService.save(EMRequestDto.toEntity(emRequestDto)));
    }

    public EMResponseDto checkEM(EMAddressDto emAddressDto) {
        ClientDto clientDto = kakaoMapClient.requestKakaoToken(appKey,
                emAddressDto.getLongitude(), emAddressDto.getLatitude());

        String city = clientDto.getDocuments().get(0).getRegion_1depth_name();
        log.info("city = {}", city);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fifteenMinutesAgo = now.minus(15, ChronoUnit.MINUTES);

        List<EmergencyMessage> emergencyMessages = emergencyMessageService.searchLatestEM(fifteenMinutesAgo, city);
        List<EMInfoDto> emInfoDtos = new ArrayList<>();
        for (EmergencyMessage emergencyMessage : emergencyMessages) {
            emInfoDtos.add(EMInfoDto.from(emergencyMessage));
        }
        return emergencyMessages.size() > 0
                ? EMResponseDto.from(emInfoDtos,true)
                : EMResponseDto.from(emInfoDtos,false);
    }


}
