package com.enjoyservice.domain.course.entity;

import com.enjoyservice.domain.common.BaseEntity;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.entity.type.*;
import com.enjoyservice.domain.coursefavorite.entity.CourseFavorite;
import com.enjoyservice.domain.courseplacesequence.entity.CoursePlaceSequence;
import com.enjoyservice.domain.coursetag.entity.CourseTag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private LikeCount likeCount = LikeCount.from(0);

    @Embedded
    private Active active = Active.from(true);

    @Column(name = "member_id")
    private String memberId;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CoursePlaceSequence> coursePlaceSequences = new ArrayList<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CourseTag> courseTags = new ArrayList<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<CourseFavorite> courseFavorites = new HashSet<>();

    @Builder
    public Course(Title title, Content content, Region region, PublicFlag publicFlag, String memberId) {
        this.title = title;
        this.content = content;
        this.region = region;
        this.publicFlag = publicFlag;
        this.memberId = memberId;
    }

    public void updateLikeCount(long value) {
        this.likeCount.updateValue(value);
    }
}
