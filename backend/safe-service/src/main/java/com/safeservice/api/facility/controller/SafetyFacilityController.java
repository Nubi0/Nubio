package com.safeservice.api.facility.controller;

import com.safeservice.api.ApiResponse;
import com.safeservice.api.facility.service.SafetyFacilityInfoService;
import com.safeservice.domain.facility.constant.FacilityType;
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
        safetyFacilityInfoService.registerSafetyBell(file, FacilityType.SAFETY_BELL);
        return ApiResponse.ok("저장완료");
    }

    @PostMapping("/lamp/upload-csv-file")
    public ApiResponse<String> saveLamp(@RequestPart("file") MultipartFile file) {
        safetyFacilityInfoService.registerSafetyBell(file, FacilityType.LAMP);
        return ApiResponse.ok("저장완료");
    }


    @PostMapping("/convenience-store/upload-csv-file")
    public ApiResponse<String> saveConvenienceStore(@RequestPart("file") MultipartFile file) {
        safetyFacilityInfoService.registerSafetyBell(file, FacilityType.CONVENIENCE_STORE);
        return ApiResponse.ok("저장완료");
    }

    @PostMapping("/police/upload-csv-file")
    public ApiResponse<String> savePolice(@RequestPart("file") MultipartFile file) {
        safetyFacilityInfoService.registerSafetyBell(file, FacilityType.POLICE);
        return ApiResponse.ok("저장완료");
    }

}
