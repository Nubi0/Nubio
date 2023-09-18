package com.enjoyservice.domain.tag.repository;

import com.enjoyservice.domain.tag.entity.Tag;
import com.enjoyservice.domain.tag.entity.type.Name;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

    boolean existsByName(Name name);
}
