package com.safeservice.api.shelter.service;

import com.safeservice.api.shelter.dto.request.NearShelter;
import com.safeservice.api.shelter.dto.response.NearShelterPageResponseDto;
import com.safeservice.domain.shelter.constant.ShelterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ShelterInfoService {

    void registerShelter(MultipartFile file, ShelterType shelterType);

    NearShelterPageResponseDto findShelterNearWithPaging(NearShelter nearShelter, ShelterType shelterType, Pageable pageable);
}