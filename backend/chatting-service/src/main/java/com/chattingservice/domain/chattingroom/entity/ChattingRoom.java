package com.chattingservice.domain.chattingroom.entity;

import com.chattingservice.domain.chattingroom.entity.constant.RoomType;
import com.chattingservice.domain.chattingroom.entity.type.*;
import com.chattingservice.domain.chattingroomprofile.entity.ChattingRoomProfile;
import com.chattingservice.domain.common.BaseTimeEntity;
import com.chattingservice.domain.notice.entity.Notice;
import com.chattingservice.domain.participant.enity.Participant;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chatting_room")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE chatting_room SET active = false WHERE id = ?")
@Where(clause = "active = true")
public class ChattingRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Title title;

    @Embedded
    private Active active = Active.from(true);

    @Embedded
    private SidoName sidoName;

    @Embedded
    private SggName sggName;

    @Embedded
    private UmdName umdName;

    @Embedded
    private RiName riName;

    @Enumerated(EnumType.STRING)
    @Column(name = "root_type", nullable = false)
    private RoomType roomType;

    @OneToOne(mappedBy = "chattingRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private ChattingRoomProfile profile;

    @OneToMany(mappedBy = "chattingRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Participant> participants = new HashSet<>();

    @OneToMany(mappedBy = "chattingRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Notice> notices = new HashSet<>();

    @Builder
    public ChattingRoom(Long id, Title title, SidoName sidoName, SggName sggName, UmdName umdName
            , RiName riName, ChattingRoomProfile profile, RoomType roomType) {
        this.id = id;
        this.title = title;
        this.sidoName = sidoName;
        this.sggName = sggName;
        this.umdName = umdName;
        this.riName = riName;
        this.profile = profile;
        this.roomType = roomType;
    }

    public void setProfile(ChattingRoomProfile chattingRoomProfile) {
        this.profile = chattingRoomProfile;
    }

    public void updateImage(ChattingRoomProfile profile) {
        this.profile = profile;
    }

}
