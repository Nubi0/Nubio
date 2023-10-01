package com.authenticationservice.domain.profile.entity;

import com.authenticationservice.domain.common.BaseEntity;
import com.authenticationservice.domain.profile.entity.type.Active;
import com.authenticationservice.domain.profile.entity.type.FileName;
import com.authenticationservice.domain.profile.entity.type.FileSize;
import com.authenticationservice.domain.profile.entity.type.FileUrl;
import com.authenticationservice.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profile")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Member member;

    @Embedded
    private FileName fileName;

    @Embedded
    private FileUrl fileUrl;

    @Embedded
    private FileSize fileSize;

    @Embedded
    private Active active = Active.from(true);

    @Builder
    public Profile(FileName fileName, FileUrl fileUrl, FileSize fileSize,Active active) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.fileSize = fileSize;
        this.active = active;
    }

    public void updateMember(Member member) {
        setMember(member);
    }

    private void setMember(Member member) {
        this.member = member;
    }

    public void withdrawProfile() {
        this.fileName.withdrawName();
        this.fileUrl.withdrawUrl();
        this.fileSize.withdrawSize();
    }
}
