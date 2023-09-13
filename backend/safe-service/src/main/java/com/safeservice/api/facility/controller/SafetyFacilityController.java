package com.safeservice.api.facility.controller;

import com.safeservice.api.ApiResponse;
import com.safeservice.api.facility.service.SafetyFacilityInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/safe")
public class SafetyFacilityController {

    private final SafetyFacilityInfoService safetyFacilityInfoService;

    @PostMapping("/safety-bell/upload-csv-file")
    public ApiResponse<String> saveSafetyBell(@RequestPart("file") MultipartFile file) {
        safetyFacilityInfoService.registerSafetyBell(file);
        return ApiResponse.ok("저장완료");
    }

}
