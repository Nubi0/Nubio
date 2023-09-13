package com.safeservice.api.emergencymessage.service;

import com.safeservice.api.emergencymessage.dto.EMInfoDto;
import com.safeservice.api.emergencymessage.dto.EMResponseDto;
import com.safeservice.api.emergencymessage.dto.client.ClientDto;
import com.safeservice.api.emergencymessage.dto.EMRequestDto;
import com.safeservice.domain.emergencymessage.entity.EmergencyMessage;
import com.safeservice.domain.emergencymessage.service.EmergencyMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public void createEmergencyMessage(EMRequestDto emRequestDto) {
        emergencyMessageService.save(EMRequestDto.toEntity(emRequestDto));
    }

    public EMResponseDto checkEM(ClientDto clientDto) {
        String city = clientDto.getDocuments().get(0).getRegion_1depth_name();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fifteenMinutesAgo = now.minus(15, ChronoUnit.MINUTES);
        List<EmergencyMessage> emergencyMessages = emergencyMessageService.searchLatestEM(fifteenMinutesAgo, city);

        List<EMInfoDto> emInfoDtos = new ArrayList<>();
        for (EmergencyMessage emergencyMessage : emergencyMessages) {
            emInfoDtos.add(EMInfoDto.from(emergencyMessage));
        }
        return EMResponseDto.from(emInfoDtos);
    }


}
