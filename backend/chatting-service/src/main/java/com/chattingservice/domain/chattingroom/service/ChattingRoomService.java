package com.chattingservice.domain.chattingroom.service;


import com.chattingservice.domain.chattingroom.entity.ChattingRoom;

import java.util.Calendar;
import java.util.List;

public interface ChattingRoomService {

    ChattingRoom matchDmParticipants(String creator,String receiver);

    ChattingRoom createDmRoom(String creator,String receiver);

    ChattingRoom createGroupRoom(String sidoName, String sggName,String umdName, String riName);
    ChattingRoom createGroupRoomForCsv(String sidoName, String sggName,String umdName, String riName);

    void createAllGroupRoom(List<ChattingRoom> chattingRooms);

    ChattingRoom inviteMembers(Long roomId, List<String> memberIds);

    ChattingRoom enterGroupRoom(Long roomId, String memberId);

    List<ChattingRoom> findMyRoomsByMemberId(String memberId);

    boolean existsRoom(Long roomId, String memberId);

    List<ChattingRoom> searchRegion(String sidoName, String sggName, String umdName, String riName);

    List<ChattingRoom> findAll();

    ChattingRoom outOfGroupRoom(Long roomId, String memberId);

    ChattingRoom findById(Long roomId);
}
