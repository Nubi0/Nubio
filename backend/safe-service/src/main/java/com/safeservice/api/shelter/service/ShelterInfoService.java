package com.safeservice.api.shelter.service;

import com.safeservice.api.shelter.dto.request.UserLocation;
import com.safeservice.api.shelter.dto.response.NearShelterPageResponseDto;
import com.safeservice.domain.shelter.constant.ShelterType;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ShelterInfoService {

    void registerShelter(MultipartFile file, ShelterType shelterType);

    NearShelterPageResponseDto findShelterNearWithPaging(UserLocation userLocation, String type, Pageable pageable);
}
