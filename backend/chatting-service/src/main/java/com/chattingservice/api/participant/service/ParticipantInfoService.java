package com.chattingservice.api.participant.service;

import com.chattingservice.api.participant.dto.response.ParticipantRes;
import com.chattingservice.global.resolver.memberInfo.MemberInfoDto;
import org.springframework.web.multipart.MultipartFile;

public interface ParticipantInfoService {


    // 회원정보 수정
    ParticipantRes updateMemberInfo(String memberId, Long roomId, MultipartFile profileImg, String nickName);

    //마지막 읽은 메시지 수정
    ParticipantRes updateLastReadMsgId(String memberId, Long roomId, String lastReadMsg);

    // memberId로 조회
    ParticipantRes findByMemberId(String memberId, Long roomId);

}
