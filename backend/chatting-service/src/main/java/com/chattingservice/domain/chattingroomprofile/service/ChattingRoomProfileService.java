package com.chattingservice.domain.chattingroomprofile.service;

import com.chattingservice.domain.chattingroom.entity.ChattingRoom;
import com.chattingservice.domain.chattingroomprofile.entity.ChattingRoomProfile;

public interface ChattingRoomProfileService {

    ChattingRoomProfile uploadProfile(String fileName, String fileUrl, Long fileSize, ChattingRoom chattingRoom);


}
