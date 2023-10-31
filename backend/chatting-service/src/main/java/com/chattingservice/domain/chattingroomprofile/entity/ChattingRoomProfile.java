package com.chattingservice.domain.chattingroomprofile.entity;

import com.chattingservice.domain.chattingroom.entity.ChattingRoom;

import com.chattingservice.domain.chattingroomprofile.entity.type.Active;
import com.chattingservice.domain.chattingroomprofile.entity.type.FileName;
import com.chattingservice.domain.chattingroomprofile.entity.type.FileSize;
import com.chattingservice.domain.chattingroomprofile.entity.type.FileUrl;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "chatting_room_profile")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE chatting_room_profile SET active = false WHERE id = ?")
@Where(clause = "active = true")
public class ChattingRoomProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatting_room_id")
    private ChattingRoom chattingRoom;

    @Embedded
    private FileName fileName;

    @Embedded
    private FileUrl fileUrl;

    @Embedded
    private FileSize fileSize;

    @Embedded
    private Active active = Active.from(true);

    @Builder
    public ChattingRoomProfile(ChattingRoom chattingRoom, FileName fileName, FileUrl fileUrl, FileSize fileSize, Active active) {
        this.chattingRoom = chattingRoom;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.fileSize = fileSize;
        this.active = active;
    }

    public void updateParticipant(ChattingRoom chattingRoom) {
        setChattingRoom(chattingRoom);
    }

    private void setChattingRoom(ChattingRoom chattingRoom) {
        this.chattingRoom = chattingRoom;
    }

    public void updateProfile(FileUrl fileUrl, FileSize fileSize) {
        this.fileUrl = fileUrl;
        this.fileSize = fileSize;
    }

}
