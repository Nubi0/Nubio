package com.safeservice.domain.safetybell.service;


import com.safeservice.domain.safetybell.entity.SafetyBell;

import java.util.List;

public interface SafetyBellService {
    SafetyBell register(SafetyBell safetyBell);

    void delete(SafetyBell safetyBell);

    List<SafetyBell> findWithinDistance(double longitude, double latitude, int distance);

}
