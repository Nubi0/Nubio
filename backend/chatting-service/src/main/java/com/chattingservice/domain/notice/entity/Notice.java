package com.chattingservice.domain.notice.entity;

import com.chattingservice.domain.chattingroom.entity.ChattingRoom;
import com.chattingservice.domain.common.BaseEntity;
import com.chattingservice.domain.notice.entity.type.Active;
import com.chattingservice.domain.notice.entity.type.Content;
import com.chattingservice.domain.notice.entity.type.EmerType;
import com.chattingservice.domain.participant.enity.Participant;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "notice")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE notice SET active = false WHERE id = ?")
@Where(clause = "active = true")
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EmerType emerType;

    @Embedded
    Active active = Active.from(true);

    @Embedded
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatting_room_id")
    private ChattingRoom chattingRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @Builder
    public Notice(Long id, EmerType emerType, Content content, ChattingRoom chattingRoom, Participant participant) {
        this.id = id;
        this.emerType = emerType;
        this.content = content;
        this.chattingRoom = chattingRoom;
        this.participant = participant;
    }

}
