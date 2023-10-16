package com.safeservice.api.emergencymessage.controller;

import com.safeservice.api.ApiResponseEntity;
import com.safeservice.api.emergencymessage.dto.EMReq;
import com.safeservice.api.emergencymessage.dto.EMResponseDto;
import com.safeservice.api.emergencymessage.dto.EMAddressDto;
import com.safeservice.api.emergencymessage.service.EmergencyMessageInfoService;
import com.safeservice.global.resolver.identification.Identification;
import com.safeservice.global.resolver.identification.IdentificationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "EmergencyMessage API", description = "재난 문자 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/safe")
public class EmergencyMessageController {

    private final EmergencyMessageInfoService emergencyMessageInfoService;

    @Operation(summary = "재난 문자 생성", description = "safe/v1/safe/emergency\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED")
    })
    @PostMapping("/emergency")
    public ApiResponseEntity<String> createEmergencyMessage(@RequestBody EMReq emReq) {
        emergencyMessageInfoService.createEmergencyMessage(emReq);
        return ApiResponseEntity.ok("생성 완료");
    }

    @Operation(summary = "API 요청을 통한 재난 문자 생성", description = "safe/v1/safe/emergency/call\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED")
    })
    @GetMapping("/emergency/call")
    public ApiResponseEntity<String> createEmergencyMessageByApi() {
        emergencyMessageInfoService.createEmergencyMessageByApi();
        return ApiResponseEntity.create("생성 완료");
    }

    @Operation(summary = "재난 여부 및 정보 조회", description = "safe/v1/safe/check\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/check")
    public ApiResponseEntity<EMResponseDto> EmergencyMessage(@RequestBody EMAddressDto emAddressDto) {
        EMResponseDto emergencyMessages = emergencyMessageInfoService.checkEM(emAddressDto);
        return ApiResponseEntity.ok(emergencyMessages);
    }
}
