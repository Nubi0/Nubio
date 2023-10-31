package com.chattingservice.api.chattingroomprofile.service;

import com.chattingservice.domain.chattingroom.entity.ChattingRoom;
import com.chattingservice.domain.participant.enity.Participant;
import org.springframework.web.multipart.MultipartFile;

public interface ChattingRoomProfileUploadService {

    //프로필 업로드
    void uploadProfile(String category, ChattingRoom chattingRoom, MultipartFile file);

}
