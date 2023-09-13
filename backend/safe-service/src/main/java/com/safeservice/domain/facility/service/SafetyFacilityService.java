package com.safeservice.domain.facility.service;

import com.safeservice.domain.facility.constant.FacilityType;
import com.safeservice.domain.facility.entity.SafetyFacility;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import java.util.List;

public interface SafetyFacilityService {

    SafetyFacility register(SafetyFacility safetyFacility);

    List<SafetyFacility> findFacilityNear(Point point, Distance distance, FacilityType facilityType);

}
