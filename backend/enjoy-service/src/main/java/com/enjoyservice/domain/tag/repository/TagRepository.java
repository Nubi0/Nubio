package com.enjoyservice.domain.tag.repository;

import com.enjoyservice.domain.tag.entity.Tag;
import com.enjoyservice.domain.tag.entity.type.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    boolean existsByName(Name name);

    Optional<Tag> findByName(Name name);

    @Query(
            "select t.id "
                    + "from Tag t  "
                    + "where t.name.value IN :tagNames "
    )
    List<Long> findAllByTags(@Param("tagNames") List<String> tagNames);
}
