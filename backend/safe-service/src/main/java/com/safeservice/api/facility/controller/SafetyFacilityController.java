package com.safeservice.api.facility.controller;

import com.safeservice.api.ApiResponseEntity;
import com.safeservice.api.facility.dto.request.UserLocation;
import com.safeservice.api.facility.dto.response.NearSafetyPageResponseDto;
import com.safeservice.api.facility.service.SafetyFacilityInfoService;
import com.safeservice.domain.facility.constant.FacilityType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "SafeFacility API", description = "안전 시설 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/safe")
public class SafetyFacilityController {

    private final SafetyFacilityInfoService safetyFacilityInfoService;

    @PostMapping("/safety-bell/upload-csv-file")
    public ApiResponseEntity<String> saveSafetyBell(@RequestPart("file") MultipartFile file) {
        safetyFacilityInfoService.registerSafetyBell(file, FacilityType.SAFETY_BELL);
        return ApiResponseEntity.ok("저장완료");
    }

    @PostMapping("/lamp/upload-csv-file")
    public ApiResponseEntity<String> saveLamp(@RequestPart("file") MultipartFile file) {
        safetyFacilityInfoService.registerSafetyBell(file, FacilityType.LAMP);
        return ApiResponseEntity.ok("저장완료");
    }


    @PostMapping("/convenience-store/upload-csv-file")
    public ApiResponseEntity<String> saveConvenienceStore(@RequestPart("file") MultipartFile file) {
        safetyFacilityInfoService.registerSafetyBell(file, FacilityType.CONVENIENCE_STORE);
        return ApiResponseEntity.ok("저장완료");
    }

    @PostMapping("/police/upload-csv-file")
    public ApiResponseEntity<String> savePolice(@RequestPart("file") MultipartFile file) {
        safetyFacilityInfoService.registerSafetyBell(file, FacilityType.POLICE);
        return ApiResponseEntity.ok("저장완료");
    }


    @Operation(summary = "근처의 안전 시설 조회", description = "safe/v1/safe/nearwith/safe-facility\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/nearwith/safe-facility")
    public ApiResponseEntity<NearSafetyPageResponseDto> findSafeStructure(@RequestParam("type") String type,
                                                                          @RequestParam("longitude") double longitude,
                                                                          @RequestParam("latitude") double latitude,
                                                                          @RequestParam("distance") double distance,
                                                                          @PageableDefault(size = 20,
                                                                            sort = "id",
                                                                            direction = Sort.Direction.ASC) Pageable pageable) {
        UserLocation userLocation = UserLocation.builder()
                .longitude(longitude)
                .latitude(latitude)
                .distance(distance)
                .build();
        NearSafetyPageResponseDto facilityNearWithPaging = safetyFacilityInfoService.findFacilityNearWithPaging(userLocation, type, pageable);
        return ApiResponseEntity.ok(facilityNearWithPaging);
    }

}
