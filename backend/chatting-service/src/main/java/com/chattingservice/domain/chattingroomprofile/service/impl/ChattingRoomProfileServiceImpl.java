package com.chattingservice.domain.chattingroomprofile.service.impl;

import com.chattingservice.domain.chattingroom.entity.ChattingRoom;
import com.chattingservice.domain.chattingroom.repository.ChattingRoomRepository;
import com.chattingservice.domain.chattingroomprofile.entity.ChattingRoomProfile;
import com.chattingservice.domain.chattingroomprofile.entity.type.FileSize;
import com.chattingservice.domain.chattingroomprofile.entity.type.FileUrl;
import com.chattingservice.domain.chattingroomprofile.repository.ChattingRoomProfileRepository;
import com.chattingservice.domain.chattingroomprofile.service.ChattingRoomProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChattingRoomProfileServiceImpl implements ChattingRoomProfileService {

    private final ChattingRoomProfileRepository chattingRoomProfileRepository;

    @Transactional
    @Override
    public ChattingRoomProfile uploadProfile(String fileName, String fileUrl, Long fileSize, ChattingRoom chattingRoom) {
        ChattingRoomProfile chattingRoomProfile = chattingRoomProfileRepository.findByChattingRoom(chattingRoom);
        chattingRoomProfile.updateProfile(FileUrl.from(fileUrl), FileSize.from(fileSize));
        return chattingRoomProfile;
    }
}
