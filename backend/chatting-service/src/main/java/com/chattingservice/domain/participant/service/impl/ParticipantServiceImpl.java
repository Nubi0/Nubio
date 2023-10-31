package com.chattingservice.domain.participant.service.impl;

import com.chattingservice.domain.chattingroom.entity.ChattingRoom;
import com.chattingservice.domain.participant.ParticipantService;
import com.chattingservice.domain.participant.enity.Participant;
import com.chattingservice.domain.participant.enity.constant.Role;
import com.chattingservice.domain.participant.enity.type.Active;
import com.chattingservice.domain.participant.enity.type.Nickname;
import com.chattingservice.domain.participant.exception.DuplicateMemberException;
import com.chattingservice.domain.participant.repository.ParticipantRepository;
import com.chattingservice.domain.profile.entity.Profile;
import com.chattingservice.domain.profile.entity.type.FileName;
import com.chattingservice.domain.profile.entity.type.FileSize;
import com.chattingservice.domain.profile.entity.type.FileUrl;
import com.chattingservice.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final String URL = "https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1698335660/noticon/edgileagzr3t7fcagplp.png";
    private final String NICKNAME = "누비오";

    @Override
    public Optional<Participant> findById(Long id) {
        return participantRepository.findByIdAndActive(id, Active.from(true));
    }

    @Override
    public Optional<Participant> findByMemberIdAndChattingRoom(String memberId, ChattingRoom chattingRoom) {
        return participantRepository.findByMemberIdAndActiveAndChattingRoom(memberId, Active.from(true), chattingRoom);
    }


    @Transactional
    @Override
    public Participant enterChattingRoom(String memberId, ChattingRoom chattingRoom) {
        Optional<Participant> optionalParticipant = participantRepository.findByMemberIdAndActiveAndChattingRoom(memberId, Active.from(true), chattingRoom);

        if (!optionalParticipant.isPresent()) {

            Participant participant = Participant.builder()
                    .memberId(memberId)
                    .nickname(Nickname.from(NICKNAME))
                    .role(Role.ROLE_USER)
                    .chattingRoom(chattingRoom)
                    .build();
            Participant saved = participantRepository.save(participant);
            saved.getNickname().setValue(NICKNAME + saved.getId());
            Profile profile = Profile.builder()
                    .participant(participant)
                    .fileName(FileName.from(saved.getNickname().getValue()))
                    .fileUrl(FileUrl.from(URL))
                    .fileSize(FileSize.from(1L))
                    .build();
            participant.setProfile(profile);

            return saved;
        }else {
            return optionalParticipant.get();
        }
    }

    @Transactional
    @Override
    public Participant register(Participant participant, ChattingRoom chattingRoom, String profileUrl, Long chattingRoomId) {
        validateDuplicateMember(participant);
        Profile profile = Profile.builder()
                .participant(participant)
                .fileName(FileName.from(participant.getNickname().getValue()))
                .fileUrl(FileUrl.from(profileUrl))
                .fileSize(FileSize.from(1L))
                .build();

        participant.setProfile(profile);
        participant.setChattingRoom(chattingRoom);
        return participantRepository.save(participant);
    }

    @Transactional
    @Override
    public void delete(Participant participant) {
        participantRepository.delete(participant);
    }


    @Transactional
    @Override
    public Participant save(Participant participant) {
        return participantRepository.save(participant);
    }

    private void validateDuplicateMember(Participant participant) {
        Optional<Participant> optionalMember = participantRepository.findByIdAndActive(participant.getId(), Active.from(true));
        if (optionalMember.isPresent()) {
            throw new DuplicateMemberException(ErrorCode.DUPLICATE_MEMBER_EXIST);
        }
    }

}
