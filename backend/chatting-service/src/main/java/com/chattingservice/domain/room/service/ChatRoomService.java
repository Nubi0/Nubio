package com.chattingservice.domain.room.service;

import com.chattingservice.domain.chatting.entity.MessageCollection;
import com.chattingservice.domain.chatting.mongo.ChatMessageRepository;
import com.chattingservice.domain.room.common.RoomIdCreator;
import com.chattingservice.domain.room.dto.*;
import com.chattingservice.domain.room.dto.request.ReqDmDto;
import com.chattingservice.domain.room.dto.request.ReqGroupDto;
import com.chattingservice.domain.room.dto.request.ReqInviteDto;
import com.chattingservice.domain.room.dto.request.ReqReadMessage;
import com.chattingservice.domain.room.dto.response.RespMyChatRoom;
import com.chattingservice.domain.room.dto.response.RespMyRoomDto;
import com.chattingservice.domain.room.entity.Member;
import com.chattingservice.domain.room.entity.RoomCollection;
import com.chattingservice.domain.room.mongo.RoomRepository;
import com.chattingservice.global.error.ErrorCode;
import com.chattingservice.global.error.exception.BusinessException;
import com.chattingservice.global.kafka.dto.request.RoomType;
import com.chattingservice.global.kafka.dto.request.RespRoomDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final RoomRepository roomRepository;
    private final ChatMessageRepository chatMessageRepository;


    public RoomCollection matchDmMembers(ReqDmDto reqDmDto) {
        return roomRepository.matchDmMembers(reqDmDto);
    }

    public RespRoomDto createDmRoom(ReqDmDto reqDmDto) {

        RoomCollection savedRoom = matchDmMembers(reqDmDto);
        if (savedRoom == null) {
            List<Member> members = new ArrayList<>();
            members.add(new Member(reqDmDto.getCreator(), LocalDateTime.now()));
            members.add(new Member(reqDmDto.getMessage_to(), LocalDateTime.now()));

            savedRoom = roomRepository.save(RoomCollection.builder()
                    .members(members)
                    .title(reqDmDto.getCreator() + "," + reqDmDto.getMessage_to())
                    .type(RoomType.DM)
                    .roomId(RoomIdCreator.createRoomId())
                    .build());
        }

        return convertEntityToDto(savedRoom);
    }

    private String createGroupTitle(String creator, List<String> list) {
        return creator + "," + String.join(",", list);
    }


    public RespRoomDto createGroup(ReqGroupDto reqGroupDto) {

        List<Member> members = new ArrayList<>();
        String title = createGroupTitle(reqGroupDto.getCreator(), reqGroupDto.getMembers());

        reqGroupDto.getMembers().forEach(m -> members.add(new Member(m, LocalDateTime.now())));
        members.add(new Member(reqGroupDto.getCreator(), LocalDateTime.now()));

        RoomCollection savedRoom = roomRepository.save(RoomCollection.builder()
                .title(title)
                .members(members)
                .type(RoomType.GROUP)
                .managers(Collections.singletonList(reqGroupDto.getCreator()))
                .roomId(RoomIdCreator.createRoomId())
                .build()
        );

        return convertEntityToDto(savedRoom);
    }

    public boolean inviteMembers(ReqInviteDto reqInviteDto) {
        long count = roomRepository.inviteMembers(reqInviteDto).getModifiedCount();
        if (count >= 1) {
            return true;
        }
        return false;
    }

    public boolean enterRoom(String roomId, String userId) {
        long count = roomRepository.enterRoom(roomId, userId).getModifiedCount();
        if (count >= 1) {
            return true;
        }
        return false;
    }


    public List<RespMyRoomDto> findAllRoom(String userId) {
        List<RoomCollection> roomCollectionList = roomRepository.findAll();
        List<RespMyRoomDto> respMyRoomDtoList = new ArrayList<>();

        for (RoomCollection roomCollection : roomCollectionList) {
            String roomId = roomCollection.getRoomId();
            MessageCollection messageCollection = chatMessageRepository.getLastMessage(roomId);
            LastMessage lastMessage = null;
            if(messageCollection!=null){
                lastMessage = LastMessage.builder()
                        .message_id(messageCollection.get_id()).sender_id(messageCollection.getSenderId())
                        .content(messageCollection.getContent()).created_at(messageCollection.getCreatedAt())
                        .build();
            }

            respMyRoomDtoList.add(RespMyRoomDto.builder()
                    .room_id(roomId).title(roomCollection.getTitle())
                    .members(roomCollection.getMembers())
                    .type(roomCollection.getType())
                    .last_message(lastMessage)
                    .my_room(roomRepository.isMyRoom(roomCollection.getRoomId(), userId))
                    .build());
        }

        return respMyRoomDtoList;
    }

    /**
     * 방마다 최신 메시지 받기
     * 1. roomid 에 해당하는 메시지 중
     * 2. 가장 최신 sort
     * 3. limit 1 개
     */
    public List<RespMyChatRoom> findMyRoomsByUserId(String userId) {
        List<RoomCollection> roomCollectionList = roomRepository.findMyRoomsByUserId(userId);

        List<RespMyChatRoom> respMyChatRooms = new ArrayList<>();

        for (RoomCollection roomCollection : roomCollectionList) {
            String roomId = roomCollection.getRoomId();
            MessageCollection messageCollection = chatMessageRepository.getLastMessage(roomId);
            LastMessage lastMessage = null;
            if(messageCollection!=null){
                lastMessage = LastMessage.builder()
                        .message_id(messageCollection.get_id()).sender_id(messageCollection.getSenderId())
                        .content(messageCollection.getContent()).created_at(messageCollection.getCreatedAt())
                        .build();
            }
            respMyChatRooms.add(RespMyChatRoom.builder()
                    .room_id(roomId).title(roomCollection.getTitle())
                    .members(roomCollection.getMembers())
                    .type(roomCollection.getType())
                    .last_message(lastMessage)
                    .build());
        }

        return respMyChatRooms;
    }

    public boolean outOfTheRoom(String roomId, String userId) {
        long count = roomRepository.outOfTheRoom(roomId, userId).getModifiedCount();
        if (count >= 1) {
            return true;
        }
        return false;
    }

    public RespRoomDto getChatRoomInfo(String roomId) {
        RoomCollection roomCollection = roomRepository.findByRoomId(roomId).orElseThrow(
                () -> new BusinessException(ErrorCode.ROOM_NOT_EXIST));
        return convertEntityToDto(roomCollection);
    }

    public boolean existsRoom(String roomId) {
        return roomRepository.existsByRoomId(roomId);
    }

    private RespRoomDto convertEntityToDto(RoomCollection roomCollection) {
        return RespRoomDto.builder()
                .room_id(roomCollection.getRoomId())
                .title(roomCollection.getTitle())
                .type(roomCollection.getType())
                .members(roomCollection.getMembers())
                .managers(roomCollection.getManagers())
                .createdAt(roomCollection.getCreatedAt())
                .build();
    }


    public Long updateLastReadMsgId(ReqReadMessage reqReadMessage) {
        return roomRepository.updateLastReadMsgId(reqReadMessage).getModifiedCount();
    }

}
