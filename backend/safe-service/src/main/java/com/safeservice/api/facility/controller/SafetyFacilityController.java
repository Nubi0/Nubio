package com.safeservice.api.facility.controller;

import com.safeservice.api.ApiResponse;
import com.safeservice.api.facility.dto.request.NearSafetyFacility;
import com.safeservice.api.facility.dto.response.NearSafetyPageResponseDto;
import com.safeservice.api.facility.service.SafetyFacilityInfoService;
import com.safeservice.domain.facility.constant.FacilityType;
import com.safeservice.domain.facility.entity.SafetyFacility;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/nearwith/safe-bell")
    public ApiResponse<NearSafetyPageResponseDto> findSafeBellNearWithPaging(@Valid  @RequestBody NearSafetyFacility nearSafetyFacility
            , @PageableDefault(size = 20,
            sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {

        NearSafetyPageResponseDto facilityNearWithPaging = safetyFacilityInfoService.findFacilityNearWithPaging(nearSafetyFacility, FacilityType.SAFETY_BELL, pageable);
        return ApiResponse.ok(facilityNearWithPaging);
    }

    @PostMapping("/nearwith/lamp")
    public ApiResponse<NearSafetyPageResponseDto>  findLampNearWithPaging(@Valid  @RequestBody NearSafetyFacility nearSafetyFacility
            , @PageableDefault(size = 20,
            sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {

        NearSafetyPageResponseDto facilityNearWithPaging = safetyFacilityInfoService.findFacilityNearWithPaging(nearSafetyFacility, FacilityType.LAMP, pageable);
        return ApiResponse.ok(facilityNearWithPaging);
    }

    @PostMapping("/nearwith/convenience-store")
    public ApiResponse<NearSafetyPageResponseDto> findConvenienceStoreNearWithPaging(@Valid  @RequestBody NearSafetyFacility nearSafetyFacility
            , @PageableDefault(size = 20,
            sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {

        NearSafetyPageResponseDto facilityNearWithPaging = safetyFacilityInfoService.findFacilityNearWithPaging(nearSafetyFacility, FacilityType.CONVENIENCE_STORE, pageable);
        return ApiResponse.ok(facilityNearWithPaging);
    }

    @PostMapping("/nearwith/police")
    public ApiResponse<NearSafetyPageResponseDto> findPoliceNearWithPaging(@Valid  @RequestBody NearSafetyFacility nearSafetyFacility
            , @PageableDefault(size = 20,
            sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {

        NearSafetyPageResponseDto facilityNearWithPaging = safetyFacilityInfoService.findFacilityNearWithPaging(nearSafetyFacility, FacilityType.POLICE, pageable);
        return ApiResponse.ok(facilityNearWithPaging);
    }

}
