package com.safeservice.api.shelter.service;

import com.safeservice.domain.shelter.constant.ShelterType;
import org.springframework.web.multipart.MultipartFile;

public interface ShelterInfoService {

    void registerShelter(MultipartFile file, ShelterType shelterType);


}
