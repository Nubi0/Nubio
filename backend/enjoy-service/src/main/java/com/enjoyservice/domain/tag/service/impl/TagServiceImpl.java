package com.enjoyservice.domain.tag.service.impl;

import com.enjoyservice.domain.tag.entity.Tag;
import com.enjoyservice.domain.tag.entity.type.Name;
import com.enjoyservice.domain.tag.repository.TagRepository;
import com.enjoyservice.domain.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Tag> findByName(Name name) {
        return tagRepository.findByName(name);
    }

    @Override
    public List<Long> findAllByTags(List<String> tagNames) {
        return tagRepository.findAllByTags(tagNames);
    }
}
