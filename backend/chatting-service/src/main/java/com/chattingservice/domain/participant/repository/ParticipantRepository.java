package com.chattingservice.domain.participant.repository;

import com.chattingservice.domain.chattingroom.entity.ChattingRoom;
import com.chattingservice.domain.participant.enity.Participant;
import com.chattingservice.domain.participant.enity.type.Active;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    Optional<Participant> findByIdAndActive(Long id, Active active);

    Optional<Participant> findByMemberIdAndActiveAndChattingRoom(String memberId, Active active, ChattingRoom chattingRoom);
    Optional<Participant> findByMemberIdAndChattingRoom(String memberId, ChattingRoom chattingRoom);
}
