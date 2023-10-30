package com.chattingservice.domain.participant.enity;

import com.chattingservice.domain.chattingroom.entity.ChattingRoom;
import com.chattingservice.domain.notice.entity.Notice;
import com.chattingservice.domain.participant.enity.constant.Role;
import com.chattingservice.domain.participant.enity.type.Active;
import com.chattingservice.domain.participant.enity.type.Nickname;
import com.chattingservice.domain.common.BaseTimeEntity;
import com.chattingservice.domain.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "participant")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE participant SET active = false WHERE id = ?  ")
public class Participant extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", unique = true, nullable = false)
    private String memberId;

    @Embedded
    private Active active = Active.from(true);

    @Embedded
    private Nickname nickname;

    @OneToOne(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Profile profile;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.ROLE_USER;

    @Column(name = "last_read_msg_id")
    private String lastReadMsgId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatting_room_id", nullable = false)
    private ChattingRoom chattingRoom;


    @OneToMany(mappedBy = "participant", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Notice> notices = new HashSet<>();


    @Builder
    public Participant(Long id, String memberId, Nickname nickname
            , Profile profile, Role role, String lastReadMsgId, ChattingRoom chattingRoom) {
        this.id = id;
        this.memberId = memberId;
        this.nickname = nickname;
        this.profile = profile;
        this.role = role;
        this.lastReadMsgId = lastReadMsgId;
        this.chattingRoom = chattingRoom;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setChattingRoom(ChattingRoom chattingRoom) {
        this.chattingRoom = chattingRoom;
    }

    public void setLastReadMsgId(String lastReadMsgId) {
        this.lastReadMsgId = lastReadMsgId;
    }

    public void withdraw() {
        this.active = Active.from(false);
        UUID uuid = UUID.randomUUID();
        this.memberId = uuid.toString();
    }

    public void updateImage(Profile profile) {
        this.profile = profile;
    }

    public void exitChattingRoom() {
        this.active = Active.from(false);
    }

    public void reEnterChattingRoom() {
        this.active = Active.from(true);
    }
}
