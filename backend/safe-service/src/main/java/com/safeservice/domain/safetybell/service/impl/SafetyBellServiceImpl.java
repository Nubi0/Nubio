package com.safeservice.domain.safetybell.service.impl;

import com.safeservice.domain.safetybell.entity.SafetyBell;
import com.safeservice.domain.safetybell.mongo.SafetyBellRepository;
import com.safeservice.domain.safetybell.service.SafetyBellService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SafetyBellServiceImpl implements SafetyBellService {

    private final SafetyBellRepository safetyBellRepository;

    @Override
    @Transactional
    public SafetyBell register(SafetyBell safetyBell) {
        return safetyBellRepository.save(safetyBell);
    }

    @Override
    @Transactional
    public void delete(SafetyBell safetyBell) {

    }

    @Override
    public List<SafetyBell> findWithinDistance(double longitude, double latitude, double distance) {
        return safetyBellRepository.findByLocationNear(new Point(longitude, latitude), new Distance(distance, Metrics.KILOMETERS));
    }
}
