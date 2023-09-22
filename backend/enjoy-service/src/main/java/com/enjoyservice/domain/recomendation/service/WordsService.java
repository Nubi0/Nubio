package com.enjoyservice.domain.recomendation.service;

import com.enjoyservice.domain.recomendation.entity.Words;

import java.util.List;

public interface WordsService {
    void saveWords(List<Words> wordsList);
}
