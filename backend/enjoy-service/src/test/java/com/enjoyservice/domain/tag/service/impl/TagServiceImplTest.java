package com.enjoyservice.domain.tag.service.impl;

import com.enjoyservice.domain.tag.entity.Tag;
import com.enjoyservice.domain.tag.entity.type.Name;
import com.enjoyservice.domain.tag.repository.TagRepository;
import com.enjoyservice.domain.tag.service.TagService;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TagServiceImplTest {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagService tagService;

    @Autowired
    private EntityManager em;

    private List<Tag> savedBeforeTags;

    @BeforeEach
    void setUp() {
        /* Tag 5개 생성 */
        savedBeforeTags = new ArrayList<>();
        for(int i = 1; i <= 5; i++) {
            Tag tag = Tag.from("name" + i);
            Tag savedTag = tagRepository.saveAndFlush(tag);
            savedBeforeTags.add(savedTag);
        }
        em.clear();
    }

    @DisplayName("이미 있는 Tag는 false를 반환한다.")
    @Test
    void isExistByName() {
        // given
        String targetName = "newName";
        Tag targetTag = Tag.from(targetName);
        Tag savedTargetTag = tagRepository.saveAndFlush(targetTag);
        em.clear();
        // when
        boolean result = tagService.isExistByName(Name.from(targetName));
        // then
        Assertions.assertThat(result).isTrue();
    }

    @DisplayName("없는 Tag는 true를 반환한다.")
    @Test
    void isNotExistByName() {
        // given
        String targetName = "newName";
        // when
        boolean result = tagService.isExistByName(Name.from(targetName));
        // then
        Assertions.assertThat(result).isFalse();
    }
}