package com.safeservice.domain.facility.mongo;

import com.safeservice.domain.facility.constant.FacilityType;
import com.safeservice.domain.facility.entity.SafetyFacility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SafetyFacilityRepository extends MongoRepository<SafetyFacility, String> {

    List<SafetyFacility> findByLocationNearAndFacilityTypeIs(Point location, Distance distance, FacilityType facilityType);

    Page<SafetyFacility> findByLocationNearAndFacilityTypeIs(Point location, Distance distance, FacilityType facilityType, Pageable pageable);

}
