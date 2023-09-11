package com.safeservice.domain.safetybell.service.impl;

import com.safeservice.domain.safetybell.entity.SafetyBell;
import com.safeservice.domain.safetybell.mongo.SafetyBellRepository;
import com.safeservice.domain.safetybell.service.SafetyBellService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SafetyBellServiceImpl implements SafetyBellService {

    private final SafetyBellRepository safetyBellRepository;

    @PersistenceContext
    private EntityManager entityManager;

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
    public List<SafetyBell> findWithinDistance(double longitude, double latitude, int distance) {
        return null;
    }
}
