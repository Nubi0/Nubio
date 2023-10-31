package com.chattingservice.domain.profile.entity;

import com.chattingservice.domain.participant.enity.Participant;
import com.chattingservice.domain.profile.entity.type.Active;
import com.chattingservice.domain.profile.entity.type.FileName;
import com.chattingservice.domain.profile.entity.type.FileSize;
import com.chattingservice.domain.profile.entity.type.FileUrl;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "profile")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE profile SET active = false WHERE id = ?")
@Where(clause = "active = true")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @Embedded
    private FileName fileName;

    @Embedded
    private FileUrl fileUrl;

    @Embedded
    private FileSize fileSize;

    @Embedded
    private Active active = Active.from(true);

    @Builder
    public Profile(Participant participant, FileName fileName, FileUrl fileUrl, FileSize fileSize) {
        this.participant = participant;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.fileSize = fileSize;
    }

    public void updateParticipant(Participant participant) {
        setParticipant(participant);
    }

    private void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public void updateProfile(FileUrl fileUrl, FileSize fileSize) {
        this.fileUrl = fileUrl;
        this.fileSize = fileSize;
    }

    public void withdrawProfile() {
        this.fileName.withdrawName();
        this.fileUrl.withdrawUrl();
        this.fileSize.withdrawSize();
    }
}