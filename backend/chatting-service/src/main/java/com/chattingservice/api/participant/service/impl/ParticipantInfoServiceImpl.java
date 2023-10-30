package com.chattingservice.api.participant.service.impl;

import com.chattingservice.api.participant.dto.response.ParticipantRes;
import com.chattingservice.api.participant.service.ParticipantInfoService;
import com.chattingservice.api.profile.service.ProfileUploadService;
import com.chattingservice.domain.chattingroom.entity.ChattingRoom;
import com.chattingservice.domain.chattingroom.repository.ChattingRoomRepository;
import com.chattingservice.domain.participant.ParticipantService;
import com.chattingservice.domain.participant.enity.Participant;
import com.chattingservice.domain.participant.repository.ParticipantRepository;
import com.chattingservice.domain.profile.service.ProfileService;
import com.chattingservice.global.error.ErrorCode;
import com.chattingservice.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ParticipantInfoServiceImpl implements ParticipantInfoService {

    private final ParticipantService participantService;
    private final ChattingRoomRepository chattingRoomRepository;
    private final ProfileUploadService profileUploadService;

    @Transactional
    @Override
    public ParticipantRes updateMemberInfo(String memberId, Long roomId, MultipartFile profileImg, String nickName) {

        Participant participant = validateParticipate(memberId, roomId);

        if (profileImg != null) {
            profileUploadService.uploadProfile("participant",participant,profileImg);
        }
        if(nickName!=null) participant.getNickname().setValue(nickName);

        return ParticipantRes.of(participant);
    }

    @Transactional
    @Override
    public ParticipantRes updateLastReadMsgId(String memberId, Long roomId, String lastReadMsg) {
        Participant participant = validateParticipate(memberId, roomId);
        participant.setLastReadMsgId(lastReadMsg);

        return ParticipantRes.of(participant);
    }

    private Participant validateParticipate(String memberId, Long roomId) {
        ChattingRoom chattingRoom = chattingRoomRepository.findById(roomId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ROOM_NOT_EXIST));
        Participant participant = participantService.findByMemberIdAndChattingRoom(memberId, chattingRoom).orElseThrow(
                () -> new BusinessException(ErrorCode.PARTICIPANT_NOT_EXIST)
        );
        return participant;
    }


    @Override
    public ParticipantRes findByMemberId(String memberId, Long roomId) {

        Participant participant = validateParticipate(memberId, roomId);

        return ParticipantRes.of(participant);
    }
}
