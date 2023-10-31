package com.chattingservice.domain.participant;

import com.chattingservice.domain.chattingroom.entity.ChattingRoom;
import com.chattingservice.domain.participant.enity.Participant;

import java.util.List;
import java.util.Optional;

public interface ParticipantService{
    Optional<Participant> findById(Long id);
    Optional<Participant> findByMemberIdAndChattingRoom(String memberId, ChattingRoom chattingRoom);

    Participant enterChattingRoom(String memberId, ChattingRoom chattingRoom);

    Participant register(Participant participant, ChattingRoom chattingRoom, String profileUrl, Long chattingRoomId);

    void delete(Participant participant);


    Participant save(Participant participant);
}
