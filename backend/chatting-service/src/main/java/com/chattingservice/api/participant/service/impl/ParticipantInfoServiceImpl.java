package com.chattingservice.api.participant.service.impl;

import com.chattingservice.api.participant.dto.response.ParticipantRes;
import com.chattingservice.api.participant.service.ParticipantInfoService;
import com.chattingservice.global.resolver.memberInfo.MemberInfoDto;
import org.springframework.web.multipart.MultipartFile;

public class ParticipantInfoServiceImpl implements ParticipantInfoService {


    @Override
    public ParticipantRes register(MemberInfoDto memberInfo, Long roomId, MultipartFile profileImg, String nickName) {
        return null;
    }

    @Override
    public ParticipantRes updateMemberInfo(MemberInfoDto memberInfo, Long roomId, MultipartFile profileImg, String nickName) {
        return null;
    }

    @Override
    public ParticipantRes updateLastReadMsgId(String lastReadMsg) {
        return null;
    }

    @Override
    public void deleteParticipant(String memberId, Long roomId) {

    }

    @Override
    public ParticipantRes findByMemberId(String memberId, Long roomId) {
        return null;
    }
}
