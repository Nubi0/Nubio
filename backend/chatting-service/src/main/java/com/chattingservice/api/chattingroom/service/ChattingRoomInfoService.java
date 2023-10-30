package com.chattingservice.api.chattingroom.service;

import com.chattingservice.api.chattingroom.dto.request.ChattingRoomEnterReq;
import com.chattingservice.api.chattingroom.dto.request.ChattingRoomOutReq;
import com.chattingservice.api.chattingroom.dto.request.ChattingRoomSearchReq;
import com.chattingservice.api.chattingroom.dto.response.ChattingRoomResp;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ChattingRoomInfoService {

    void roomCsvToDb(MultipartFile file);

    // 그룹 방 입장
    ChattingRoomResp enterGroupRoom(ChattingRoomEnterReq chattingRoomEnterReq);

    // 그룹 방 나가기
    ChattingRoomResp outOfGroupRoom(ChattingRoomOutReq chattingRoomOutReq);

    //지역으로 검색
    List<ChattingRoomResp> searchRegion(ChattingRoomSearchReq chattingRoomSearchReq);

}
