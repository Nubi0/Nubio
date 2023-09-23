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
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
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
        assertThat(result).isTrue();
    }

    @DisplayName("없는 Tag는 true를 반환한다.")
    @Test
    void isNotExistByName() {
        // given
        String targetName = "newName";
        // when
        boolean result = tagService.isExistByName(Name.from(targetName));
        // then
        assertThat(result).isFalse();
    }

    @DisplayName("존재하는 Tag를 Name으로 조회하기")
    @Test
    void findByName() {
        // given
        String name = "target";
        Tag tag = Tag.from(name);
        Tag savedTag = tagRepository.saveAndFlush(tag);
        em.clear();
        // when
        Optional<Tag> opTag = tagService.findByName(Name.from(name));
        // then
        assertThat(opTag).isNotEmpty();
        assertThat(opTag.get().getName().getValue()).isEqualTo(name);
    }

    @DisplayName("존재하지 않는 Tag를 Name으로 조회하기")
    @Test
    void notExistFindByName() {
        // given
        String name = "target";
        // when
        Optional<Tag> opTag = tagService.findByName(Name.from(name));
        // then
        assertThat(opTag).isEmpty();
    }
}