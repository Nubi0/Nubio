package com.enjoyservice.domain.tag.entity;

import com.enjoyservice.domain.common.BaseEntity;
import com.enjoyservice.domain.tag.entity.type.Active;
import com.enjoyservice.domain.tag.entity.type.Name;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private Active active;

}
