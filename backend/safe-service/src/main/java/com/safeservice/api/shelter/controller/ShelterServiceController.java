package com.safeservice.api.shelter.controller;

import com.safeservice.api.ApiResponse;
import com.safeservice.api.shelter.dto.request.NearShelter;
import com.safeservice.api.shelter.dto.response.NearShelterPageResponseDto;
import com.safeservice.api.shelter.service.ShelterInfoService;
import com.safeservice.domain.shelter.constant.ShelterType;
import jakarta.validation.Valid;
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
    public ApiResponse<String> saveSchool(@RequestPart("file") MultipartFile file) {
        shelterInfoService.registerShelter(file, ShelterType.SCHOOL);
        return ApiResponse.ok("저장완료");
    }

    @PostMapping("/hospital/upload-csv-file")
    public ApiResponse<String> saveHospital(@RequestPart("file") MultipartFile file) {
        shelterInfoService.registerShelter(file, ShelterType.HOSPITAL);
        return ApiResponse.ok("저장완료");
    }
    @PostMapping("/fire-station/upload-csv-file")
    public ApiResponse<String> saveFireStation(@RequestPart("file") MultipartFile file) {
        shelterInfoService.registerShelter(file, ShelterType.FIRE_STATION);
        return ApiResponse.ok("저장완료");
    }
    @PostMapping("/subway/upload-csv-file")
    public ApiResponse<String> saveSubway(@RequestPart("file") MultipartFile file) {
        shelterInfoService.registerShelter(file, ShelterType.SUBWAY);
        return ApiResponse.ok("저장완료");
    }
    @PostMapping("/civil-defense/upload-csv-file")
    public ApiResponse<String> saveCivilDefense(@RequestPart("file") MultipartFile file) {
        shelterInfoService.registerShelter(file, ShelterType.CIVIL_DEFENSE);
        return ApiResponse.ok("저장완료");
    }

    @PostMapping("/nearwith/school")
    public ApiResponse<NearShelterPageResponseDto> findSchoolNearWithPaging(@Valid @RequestBody NearShelter nearShelter
            , @PageableDefault(size = 20,
            sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
        NearShelterPageResponseDto shelterNearWithPaging = shelterInfoService.findShelterNearWithPaging(nearShelter, ShelterType.SCHOOL, pageable);
        return ApiResponse.ok(shelterNearWithPaging);
    }

    @PostMapping("/nearwith/hospital")
    public ApiResponse<NearShelterPageResponseDto> findHospitalNearWithPaging(@Valid @RequestBody NearShelter nearShelter
            , @PageableDefault(size = 20,
            sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
        NearShelterPageResponseDto shelterNearWithPaging = shelterInfoService.findShelterNearWithPaging(nearShelter, ShelterType.HOSPITAL, pageable);
        return ApiResponse.ok(shelterNearWithPaging);
    }

    @PostMapping("/nearwith/fire-station")
    public ApiResponse<NearShelterPageResponseDto> findFireStationNearWithPaging(@Valid @RequestBody NearShelter nearShelter
            , @PageableDefault(size = 20,
            sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
        NearShelterPageResponseDto shelterNearWithPaging = shelterInfoService.findShelterNearWithPaging(nearShelter, ShelterType.FIRE_STATION, pageable);
        return ApiResponse.ok(shelterNearWithPaging);
    }

    @PostMapping("/nearwith/subway")
    public ApiResponse<NearShelterPageResponseDto> findSubwayNearWithPaging(@Valid @RequestBody NearShelter nearShelter
            , @PageableDefault(size = 20,
            sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
        NearShelterPageResponseDto shelterNearWithPaging = shelterInfoService.findShelterNearWithPaging(nearShelter, ShelterType.SUBWAY, pageable);
        return ApiResponse.ok(shelterNearWithPaging);
    }

    @PostMapping("/nearwith/civil-defense")
    public ApiResponse<NearShelterPageResponseDto> findCivilDefenseNearWithPaging(@Valid @RequestBody NearShelter nearShelter
            , @PageableDefault(size = 20,
            sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
        NearShelterPageResponseDto shelterNearWithPaging = shelterInfoService.findShelterNearWithPaging(nearShelter, ShelterType.CIVIL_DEFENSE, pageable);
        return ApiResponse.ok(shelterNearWithPaging);
    }
}
