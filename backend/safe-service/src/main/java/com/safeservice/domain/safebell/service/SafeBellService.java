package com.safeservice.domain.safebell.service;

import com.safeservice.domain.safebell.entity.SafeBell;

import java.util.List;

public interface SafeBellService {

    SafeBell register(SafeBell safeBell);

    void delete(SafeBell safeBell);

    List<SafeBell> findWithinDistance(double longitude, double latitude, int distance);

}
