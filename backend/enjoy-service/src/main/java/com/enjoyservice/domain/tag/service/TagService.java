package com.enjoyservice.domain.tag.service;

import com.enjoyservice.domain.tag.entity.Tag;
import com.enjoyservice.domain.tag.entity.type.Name;

import java.util.List;
import java.util.Optional;

public interface TagService {
    Tag save(Tag tag);

    boolean isExistByName(Name name);

    Optional<Tag> findByName(Name name);
}
