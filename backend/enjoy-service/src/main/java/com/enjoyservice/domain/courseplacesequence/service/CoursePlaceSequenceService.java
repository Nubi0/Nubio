package com.enjoyservice.domain.courseplacesequence.service;

import com.enjoyservice.domain.courseplacesequence.entity.CoursePlaceSequence;

import java.util.List;

public interface CoursePlaceSequenceService {

    List<CoursePlaceSequence> saveAll(List<CoursePlaceSequence> coursePlaceSequences);
}
