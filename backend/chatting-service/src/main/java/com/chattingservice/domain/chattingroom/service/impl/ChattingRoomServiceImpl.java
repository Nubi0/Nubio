package com.chattingservice.domain.chattingroom.service.impl;

import com.chattingservice.domain.chatting.mongo.ChatMessageRepository;
import com.chattingservice.domain.chattingroom.entity.ChattingRoom;
import com.chattingservice.domain.chattingroom.entity.constant.RoomType;
import com.chattingservice.domain.chattingroom.entity.type.*;
import com.chattingservice.domain.chattingroom.repository.ChattingRoomRepository;
import com.chattingservice.domain.chattingroom.service.ChattingRoomService;
import com.chattingservice.domain.chattingroomprofile.entity.ChattingRoomProfile;
import com.chattingservice.domain.chattingroomprofile.entity.type.Active;
import com.chattingservice.domain.chattingroomprofile.entity.type.FileName;
import com.chattingservice.domain.chattingroomprofile.entity.type.FileSize;
import com.chattingservice.domain.chattingroomprofile.entity.type.FileUrl;
import com.chattingservice.domain.participant.ParticipantService;
import com.chattingservice.domain.participant.enity.Participant;
import com.chattingservice.domain.participant.repository.ParticipantRepository;
import com.chattingservice.global.error.ErrorCode;
import com.chattingservice.global.error.exception.BusinessException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChattingRoomServiceImpl implements ChattingRoomService {

    @PersistenceContext
    private EntityManager entityManager;

    private final ChattingRoomRepository chattingRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ParticipantRepository participantRepository;
    private final ParticipantService participantService;
    private final String URL = "https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1698335660/noticon/edgileagzr3t7fcagplp.png";

    @Override
    public ChattingRoom matchDmParticipants(String creator, String receiver) {
        return chattingRoomRepository.matchDmParticipants(creator, receiver);
    }

    @Override
    public ChattingRoom createDmRoom(String creator, String receiver) {
        return null;
    }

    @Override
    public ChattingRoom createGroupRoom(String sidoName, String sggName, String umdName, String riName) {
        StringBuilder sb = new StringBuilder();

        sb.append(sidoName);
        if (!sggName.isEmpty()) {
            sb.append("/" + sggName);
        }
        if (!umdName.isEmpty()) {
            sb.append("/" + umdName);
        }
        if (!riName.isEmpty()) {
            sb.append("/" + riName);
        }
        ChattingRoom chattingRoom = ChattingRoom.builder().
                title(Title.from(sb.toString()))
                .sidoName(SidoName.builder().name(sidoName).build())
                .sggName(SggName.builder().name(sggName).build())
                .umdName(UmdName.builder().name(umdName).build())
                .riName(RiName.builder().name(riName).build())
                .roomType(RoomType.GROUP)
                .build();

        ChattingRoomProfile chattingRoomProfile = ChattingRoomProfile.builder()
                .chattingRoom(chattingRoom)
                .fileName(FileName.from(chattingRoom.getTitle().getValue()))
                .fileUrl(FileUrl.from(URL))
                .fileSize(FileSize.from(1L))
                .active(Active.from(true))
                .build();

        chattingRoom.setProfile(chattingRoomProfile);

        return chattingRoomRepository.save(chattingRoom);
    }

    @Override
    public ChattingRoom createGroupRoomForCsv(String sidoName, String sggName, String umdName, String riName) {
        StringBuilder sb = new StringBuilder();

        sb.append(sidoName);
        if (!sggName.isEmpty()) {
            sb.append("/" + sggName);
        }
        if (!umdName.isEmpty()) {
            sb.append("/" + umdName);
        }
        if (!riName.isEmpty()) {
            sb.append("/" + riName);
        }
        ChattingRoom chattingRoom = ChattingRoom.builder().
                title(Title.from(sb.toString()))
                .sidoName(SidoName.builder().name(sidoName).build())
                .sggName(SggName.builder().name(sggName).build())
                .umdName(UmdName.builder().name(umdName).build())
                .riName(RiName.builder().name(riName).build())
                .roomType(RoomType.GROUP)
                .build();

        ChattingRoomProfile chattingRoomProfile = ChattingRoomProfile.builder()
                .chattingRoom(chattingRoom)
                .fileName(FileName.from(chattingRoom.getTitle().getValue()))
                .fileUrl(FileUrl.from(URL))
                .fileSize(FileSize.from(1L))
                .active(Active.from(true))
                .build();

        chattingRoom.setProfile(chattingRoomProfile);
        return chattingRoom;
    }

    @Override
    public void createAllGroupRoom(List<ChattingRoom> chattingRooms) {
        chattingRoomRepository.saveAll(chattingRooms);
    }

    @Override
    public ChattingRoom inviteMembers(Long roomId, List<String> memberIds) {
        return null;
    }

    @Transactional
    @Override
    public ChattingRoom enterGroupRoom(Long roomId, String memberId) {
        ChattingRoom chattingRoom = chattingRoomRepository.findById(roomId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ROOM_NOT_EXIST));

        if (chattingRoom.getRoomType().name() != RoomType.GROUP.name()) {
            throw new BusinessException(ErrorCode.ROOM_NOT_GROUP_ERROR);
        }

        Optional<Participant> exitParticipant = participantRepository.findByMemberIdAndActiveAndChattingRoom(memberId
                , com.chattingservice.domain.participant.enity.type.Active.from(false), chattingRoom);
        Optional<Participant> optional = participantRepository.findByMemberIdAndActiveAndChattingRoom(memberId
                , com.chattingservice.domain.participant.enity.type.Active.from(true), chattingRoom);
        if (!optional.isPresent()) {
            participantService.enterChattingRoom(memberId, chattingRoom);
        }else if(exitParticipant.isPresent()){
            exitParticipant.get().getActive().setValue(true);
        }

        return chattingRoom;
    }

    @Transactional
    @Override
    public ChattingRoom enterGroupRoomWithProfile(String memberId, Long roomId, String nickName) {
        ChattingRoom chattingRoom = chattingRoomRepository.findById(roomId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ROOM_NOT_EXIST));


        if (chattingRoom.getRoomType().name() != RoomType.GROUP.name()) {
            throw new BusinessException(ErrorCode.ROOM_NOT_GROUP_ERROR);
        }

        Optional<Participant> exitParticipant = participantRepository.findByMemberIdAndActiveAndChattingRoom(memberId
                , com.chattingservice.domain.participant.enity.type.Active.from(false), chattingRoom);
        Optional<Participant> optional = participantRepository.findByMemberIdAndActiveAndChattingRoom(memberId
                , com.chattingservice.domain.participant.enity.type.Active.from(true), chattingRoom);
        if (optional.isPresent()) {
            optional.get().getNickname().setValue(nickName);
        }else if(exitParticipant.isPresent()){
            exitParticipant.get().getActive().setValue(true);
            exitParticipant.get().getNickname().setValue(nickName);
        } else{
            participantService.enterGroupRoomWithProfile(memberId, nickName, chattingRoom);
        }

        return chattingRoom;
    }

    @Override
    public List<ChattingRoom> findMyRoomsByMemberId(String memberId) {
        return chattingRoomRepository.findMyRoomsByMemberId(memberId);
    }

    @Override
    public boolean existsRoom(Long roomId, String memberId) {
        ChattingRoom chattingRoom = chattingRoomRepository.findById(roomId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ROOM_NOT_EXIST));
        return participantRepository.findByMemberIdAndActiveAndChattingRoom(memberId,com.chattingservice.domain.participant.enity.type.Active.from(true), chattingRoom).isPresent();
    }

    @Override
    public List<ChattingRoom> searchRegion(String sidoName, String sggName, String umdName, String riName) {
        if (sidoName == null) {
            return findAll();
        } else if (sggName == null) {
            return chattingRoomRepository.findBySidoName(SidoName.from(sidoName));
        } else if (umdName == null) {
            return chattingRoomRepository.findBySidoNameAndSggName(SidoName.from(sidoName), SggName.from(sggName));
        } else if (riName == null) {
            return chattingRoomRepository.findBySidoNameAndSggNameAndUmdName(SidoName.from(sidoName), SggName.from(sggName), UmdName.from(umdName));
        } else {
            return chattingRoomRepository.findBySidoNameAndSggNameAndUmdNameAndRiName(SidoName.from(sidoName), SggName.from(sggName),  UmdName.from(umdName), RiName.from(riName));
        }
    }

    @Override
    public List<ChattingRoom> findAll() {
        return chattingRoomRepository.findAll();
    }

    @Transactional
    @Override
    public ChattingRoom outOfGroupRoom(Long roomId, String memberId) {
        ChattingRoom chattingRoom = chattingRoomRepository.findById(roomId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ROOM_NOT_EXIST));

        if (chattingRoom.getRoomType().name() != RoomType.GROUP.name()) {
            throw new BusinessException(ErrorCode.ROOM_NOT_GROUP_ERROR);
        }

        Participant participant = participantRepository.findByMemberIdAndActiveAndChattingRoom(memberId, com.chattingservice.domain.participant.enity.type.Active.from(true),chattingRoom)
                .orElseThrow(() -> new BusinessException(ErrorCode.PARTICIPANT_NOT_EXIST));

        participant.withdraw();
        chattingRoom = chattingRoomRepository.findById(roomId).get();
        return  chattingRoom;
    }

    @Override
    public ChattingRoom findById(Long roomId) {
        return chattingRoomRepository.findById(roomId).orElseThrow(
                ()->new BusinessException(ErrorCode.ROOM_NOT_EXIST));

    }


}
