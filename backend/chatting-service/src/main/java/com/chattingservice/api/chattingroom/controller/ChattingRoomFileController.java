package com.chattingservice.api.chattingroom.controller;

import com.chattingservice.api.ApiResponseEntity;
import com.chattingservice.api.chattingroom.service.ChattingRoomInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "ChattingRoomFile API", description = "채팅방 파일 저장 api")
@RestController
@RequestMapping("/v1/chatting/room")
@RequiredArgsConstructor
public class ChattingRoomFileController {

    private final ChattingRoomInfoService chattingRoomInfoService;

    @PostMapping("/upload-csv-file")
    public ApiResponseEntity<String> saveNode(@RequestPart("file") MultipartFile file) {
        chattingRoomInfoService.roomCsvToDb(file);
        return ApiResponseEntity.ok("저장완료");
    }

}
