package com.enjoyservice.domain.recomendation.service.impl;

import com.enjoyservice.domain.recomendation.entity.Words;
import com.enjoyservice.domain.recomendation.mongo.WordsRepository;
import com.enjoyservice.domain.recomendation.service.WordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WordsServiceImpl implements WordsService {

    private final WordsRepository wordsRepository;

    @Override
    public void saveWords(List<Words> wordsList) {
        wordsRepository.saveAll(wordsList);
    }
}
