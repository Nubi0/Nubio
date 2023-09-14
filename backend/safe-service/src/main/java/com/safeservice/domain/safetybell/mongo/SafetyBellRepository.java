package com.safeservice.domain.safetybell.mongo;

import com.safeservice.domain.safetybell.entity.SafetyBell;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SafetyBellRepository extends MongoRepository<SafetyBell, String> {
    List<SafetyBell> findByLocationNear(Point location, Distance distance);
}
