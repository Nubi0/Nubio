package com.enjoyservice.domain.tag.service.impl;

import com.enjoyservice.domain.tag.entity.Tag;
import com.enjoyservice.domain.tag.entity.type.Name;
import com.enjoyservice.domain.tag.repository.TagRepository;
import com.enjoyservice.domain.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public boolean isExistByName(Name name) {
        return tagRepository.existsByName(name);
    }
}
