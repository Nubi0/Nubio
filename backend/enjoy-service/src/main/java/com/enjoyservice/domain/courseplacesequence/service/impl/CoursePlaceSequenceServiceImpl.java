package com.enjoyservice.domain.courseplacesequence.service.impl;

import com.enjoyservice.domain.courseplacesequence.entity.CoursePlaceSequence;
import com.enjoyservice.domain.courseplacesequence.repository.CoursePlaceSequenceRepository;
import com.enjoyservice.domain.courseplacesequence.service.CoursePlaceSequenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CoursePlaceSequenceServiceImpl implements CoursePlaceSequenceService {

    private final CoursePlaceSequenceRepository coursePlaceSequenceRepository;

    @Transactional
    @Override
    public List<CoursePlaceSequence> saveAll(List<CoursePlaceSequence> coursePlaceSequences) {
        return coursePlaceSequenceRepository.saveAll(coursePlaceSequences);
    }
}
