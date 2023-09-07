package com.safeservice.domain.safebell.service.impl;

import com.safeservice.domain.safebell.entity.SafeBell;
import com.safeservice.domain.safebell.repository.SafeBellRepository;
import com.safeservice.domain.safebell.service.SafeBellService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void delete(SafeBell safeBell) {
        safeBellRepository.delete(safeBell);
        entityManager.flush();
    }
}
