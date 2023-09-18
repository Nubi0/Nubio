package com.enjoyservice.domain.course.entity;

import com.enjoyservice.domain.common.BaseEntity;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.entity.type.Active;
import com.enjoyservice.domain.course.entity.type.Content;
import com.enjoyservice.domain.course.entity.type.PublicFlag;
import com.enjoyservice.domain.course.entity.type.Title;
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
@SQLDelete(sql = "UPDATE course SET active = false WHERE id = ?")
@Where(clause = "active = true")
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    @Enumerated(EnumType.STRING)
    private Region region;

    @Embedded
    private PublicFlag publicFlag;

    @Embedded
    private Active active = Active.from(true);

    @Column(name = "member_id")
    private String memberId;

    @Builder
    public Course(Title title, Content content, Region region, PublicFlag publicFlag, String memberId) {
        this.title = title;
        this.content = content;
        this.region = region;
        this.publicFlag = publicFlag;
        this.memberId = memberId;
    }
}
