package com.safeservice.api.shelter.controller;

import com.safeservice.api.ApiResponseEntity;
import com.safeservice.api.shelter.dto.request.UserLocation;
import com.safeservice.api.shelter.dto.response.NearShelterPageResponseDto;
import com.safeservice.api.shelter.service.ShelterInfoService;
import com.safeservice.domain.shelter.constant.ShelterType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/safe")
public class ShelterServiceController {

    private final ShelterInfoService shelterInfoService;


    @PostMapping("/school/upload-csv-file")
    public ApiResponseEntity<String> saveSchool(@RequestPart("file") MultipartFile file) {
        shelterInfoService.registerShelter(file, ShelterType.SCHOOL);
        return ApiResponseEntity.ok("저장완료");
    }

    @PostMapping("/hospital/upload-csv-file")
    public ApiResponseEntity<String> saveHospital(@RequestPart("file") MultipartFile file) {
        shelterInfoService.registerShelter(file, ShelterType.HOSPITAL);
        return ApiResponseEntity.ok("저장완료");
    }

    @PostMapping("/fire-station/upload-csv-file")
    public ApiResponseEntity<String> saveFireStation(@RequestPart("file") MultipartFile file) {
        shelterInfoService.registerShelter(file, ShelterType.FIRE_STATION);
        return ApiResponseEntity.ok("저장완료");
    }

    @PostMapping("/subway/upload-csv-file")
    public ApiResponseEntity<String> saveSubway(@RequestPart("file") MultipartFile file) {
        shelterInfoService.registerShelter(file, ShelterType.SUBWAY);
        return ApiResponseEntity.ok("저장완료");
    }

    @PostMapping("/civil-defense/upload-csv-file")
    public ApiResponseEntity<String> saveCivilDefense(@RequestPart("file") MultipartFile file) {
        shelterInfoService.registerShelter(file, ShelterType.CIVIL_DEFENSE);
        return ApiResponseEntity.ok("저장완료");
    }


    @GetMapping("/nearwith/safe-shelter")
    public ApiResponseEntity<NearShelterPageResponseDto> findSafeShelterNearWithPaging(@RequestParam("type") String type,
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
        NearShelterPageResponseDto shelterNearWithPaging = shelterInfoService.findShelterNearWithPaging(userLocation, type, pageable);
        return ApiResponseEntity.ok(shelterNearWithPaging);
    }


}
