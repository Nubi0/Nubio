package com.enjoyservice.domain.taste.service.impl;

import com.enjoyservice.domain.taste.entity.Taste;
import com.enjoyservice.domain.taste.repository.TasteRepository;
import com.enjoyservice.domain.taste.service.TasteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TasteServiceImpl implements TasteService {

    private final TasteRepository tasteRepository;

    @Override
    @Transactional
    public Taste saveTaste(Taste taste) {
        Taste savedTaste = tasteRepository.save(taste);
        return savedTaste;
    }
}
