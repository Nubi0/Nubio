package com.safeservice.domain.safebell.service.impl;

import com.safeservice.domain.safebell.dto.response.NearestSafeBellDto;
import com.safeservice.domain.safebell.entity.SafeBell;
import com.safeservice.domain.safebell.repository.SafeBellRepository;
import com.safeservice.domain.safebell.service.SafeBellService;
import com.safeservice.global.error.ErrorCode;
import com.safeservice.global.error.exception.BusinessException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SafeBellImpl implements SafeBellService {

    private final SafeBellRepository safeBellRepository;

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    @Transactional
    public SafeBell register(SafeBell safeBell) {
        return safeBellRepository.save(safeBell);
    }

    @Override
    @Transactional
    public void delete(SafeBell safeBell) {
        safeBellRepository.delete(safeBell);
        entityManager.flush();
    }

    @Override
    public List<SafeBell> findWithinDistance(double longitude, double latitude, int distance) {
        return safeBellRepository.findWithinDistance(longitude, latitude, distance);
    }

    @Override
    public NearestSafeBellDto findNearestSafeBell(double longitude, double latitude) {
        return safeBellRepository.findNearestSafeBell(longitude, latitude)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOTHING_SAFE_BELL));

    }
}
