package com.enjoyservice.domain.taste.entity;

import com.enjoyservice.domain.taste.entity.constant.DetailType;
import com.enjoyservice.domain.taste.entity.constant.Type;
import com.enjoyservice.domain.taste.entity.type.Active;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "taste")
@SQLDelete(sql = "UPDATE taste SET active = false WHERE id = ?")
@Where(clause = "active = true")
public class Taste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private DetailType detailType;

    @Embedded
    private Active active = Active.from(true);

    @Builder
    public Taste(Type type, DetailType detailType, Active active) {
        this.type = type;
        this.detailType = detailType;
        this.active = active;
    }
}
