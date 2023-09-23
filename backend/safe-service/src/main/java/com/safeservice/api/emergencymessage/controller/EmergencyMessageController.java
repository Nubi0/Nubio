package com.safeservice.api.emergencymessage.controller;

import com.safeservice.api.ApiResponseEntity;
import com.safeservice.api.emergencymessage.dto.EMReq;
import com.safeservice.api.emergencymessage.dto.EMResponseDto;
import com.safeservice.api.emergencymessage.dto.EMAddressDto;
import com.safeservice.api.emergencymessage.service.EmergencyMessageInfoService;
import com.safeservice.global.resolver.identification.Identification;
import com.safeservice.global.resolver.identification.IdentificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/safe")
public class EmergencyMessageController {

    private final EmergencyMessageInfoService emergencyMessageInfoService;

    @PostMapping("/emergency")
    public ApiResponseEntity<String> createEmergencyMessage(@Identification IdentificationDto identificationDto,
                                                            @RequestBody EMReq emReq) {
        emergencyMessageInfoService.createEmergencyMessage(emReq);
        return ApiResponseEntity.ok("생성 완료");
    }


    @PostMapping("/check")
    public ApiResponseEntity<EMResponseDto> EmergencyMessage(@Identification IdentificationDto identificationDto,
                                                             @RequestBody EMAddressDto emAddressDto) {
        EMResponseDto emergencyMessages = emergencyMessageInfoService.checkEM(emAddressDto);
        return ApiResponseEntity.ok(emergencyMessages);
    }
}
