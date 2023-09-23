package com.enjoyservice.domain.tag.entity;

import com.enjoyservice.domain.common.BaseEntity;
import com.enjoyservice.domain.tag.entity.type.Active;
import com.enjoyservice.domain.tag.entity.type.Name;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private Active active = Active.from(true);

    private Tag(final String name) {
        this.name = Name.from(name);
    }

    public static Tag from(final String name) {
        return new Tag(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag tag)) return false;
        return Objects.equals(getName().getValue(), tag.getName().getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName().getValue());
    }
}
