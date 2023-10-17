package com.safeservice.api.emergencymessage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.safeservice.api.emergencymessage.client.DataPortalApiClient;
import com.safeservice.api.emergencymessage.client.KakaoMapClient;
import com.safeservice.api.emergencymessage.dto.*;
import com.safeservice.api.emergencymessage.dto.client.ClientDto;
import com.safeservice.api.emergencymessage.dto.client.DataApiDto;
import com.safeservice.domain.emergencymessage.entity.EmergencyMessage;
import com.safeservice.domain.emergencymessage.service.EmergencyMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private final DataPortalApiClient dataPortalApiClient;

    @Value("${cloud.openfeign.client.config.feignName.appKey}")
    private String appKey;

    private String serviceKey = "ZBlbsX27bnVykE6yaeVoKS00GpfGJkqMm%2BEVd8dDP9D8w2rK8bfe1PzwM7z%2B3XskXJM2eYo4PYVC6D374zGtjQ%3D%3D";

    @Transactional
    public void createEmergencyMessage(EMReq emReq) {
        emReq.getData().forEach(emRequestDto ->
                emergencyMessageService.save(EMRequestDto.toEntity(emRequestDto)));
    }



    @Transactional
    public void createEmergencyMessageByApi() throws JsonProcessingException {
        String dataApi = dataPortalApiClient.requestDataApi(serviceKey,"1","50","json");
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(dataApi, JsonObject.class);
        JsonObject rowArray = jsonObject.getAsJsonArray("DisasterMsg")
                .get(1)
                .getAsJsonObject();
        DataApiDto dataApiDto = gson.fromJson(rowArray, DataApiDto.class);
        dataApiDto.getRow().forEach(row -> {
            if (checkInvalidTime(row.getCreateDate())) {
                emergencyMessageService.save(EMRequestDto.toEntityByApi(row));
            }
        });
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


    private boolean checkInvalidTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(time, formatter);

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime fifteenMinutesAgo = currentTime.minusMinutes(15);
        return localDateTime.isAfter(fifteenMinutesAgo);
    }

}
