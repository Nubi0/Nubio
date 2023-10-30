package com.chattingservice.api.participant.service;

import com.chattingservice.api.participant.dto.response.ParticipantRes;
import com.chattingservice.global.resolver.memberInfo.MemberInfoDto;
import org.springframework.web.multipart.MultipartFile;

public interface ParticipantInfoService {

    // 채팅방 입장
    ParticipantRes register(MemberInfoDto memberInfo, Long roomId, MultipartFile profileImg, String nickName);

    // 회원정보 수정
    ParticipantRes updateMemberInfo(MemberInfoDto memberInfo, Long roomId, MultipartFile profileImg, String nickName);

    //마지막 읽은 메시지 수정
    ParticipantRes updateLastReadMsgId(String lastReadMsg);

    // 채팅방 나가기
    void deleteParticipant(String memberId, Long roomId);

    // memberId로 조회
    ParticipantRes findByMemberId(String memberId, Long roomId);

}
