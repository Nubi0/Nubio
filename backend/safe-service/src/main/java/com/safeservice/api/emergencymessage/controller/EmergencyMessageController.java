package com.safeservice.api.emergencymessage.controller;

import com.safeservice.api.ApiResponse;
import com.safeservice.api.emergencymessage.client.KakaoMapClient;
import com.safeservice.api.emergencymessage.dto.EMResponseDto;
import com.safeservice.api.emergencymessage.dto.EMAddressDto;
import com.safeservice.api.emergencymessage.dto.EMRequestDto;
import com.safeservice.api.emergencymessage.service.EmergencyMessageInfoService;
import com.safeservice.global.resolver.identification.Identification;
import com.safeservice.global.resolver.identification.IdentificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/safe")
public class EmergencyMessageController {

    private final EmergencyMessageInfoService emergencyMessageInfoService;
    private final KakaoMapClient kakaoMapClient;

    @Value("${cloud.openfeign.client.config.feignName.appKey}")
    private String appKey;

    @PostMapping("/emergency")
    public ApiResponse<String> createEmergencyMessage(@Identification IdentificationDto identificationDto,
                                                @RequestBody EMRequestDto emRequestDto) {
        emergencyMessageInfoService.createEmergencyMessage(emRequestDto);
        return ApiResponse.ok("생성 완료");
    }


    @PostMapping("/check")
    public ApiResponse<EMResponseDto> EmergencyMessage(@RequestBody EMAddressDto emAddressDto) {
        EMResponseDto emergencyMessages = emergencyMessageInfoService.checkEM(kakaoMapClient.requestKakaoToken
                (appKey, emAddressDto.getLongitude(), emAddressDto.getLatitude()));
        return ApiResponse.ok(emergencyMessages);
    }
}
