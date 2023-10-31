package com.chattingservice.domain.chattingroomprofile.repository;

import com.chattingservice.domain.chattingroom.entity.ChattingRoom;
import com.chattingservice.domain.chattingroomprofile.entity.ChattingRoomProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRoomProfileRepository extends JpaRepository<ChattingRoomProfile, Long> {
    ChattingRoomProfile findByChattingRoom(ChattingRoom chattingRoom);
}
