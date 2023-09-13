package com.safeservice.domain.facility.service.impl;

import com.safeservice.domain.facility.constant.FacilityType;
import com.safeservice.domain.facility.entity.SafetyFacility;
import com.safeservice.domain.facility.mongo.SafetyFacilityRepository;
import com.safeservice.domain.facility.service.SafetyFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SafetyFacilityServiceImpl implements SafetyFacilityService {

    private final SafetyFacilityRepository safetyFacilityRepository;

    @Override
    @Transactional
    public SafetyFacility register(SafetyFacility safetyFacility) {
        return safetyFacilityRepository.save(safetyFacility);
    }

    @Override
    public List<SafetyFacility> findFacilityNear(Point point, Distance distance, FacilityType facilityType) {
        return safetyFacilityRepository.findByLocationNearAndFacilityTypeIs(point, distance, facilityType);
    }
}
