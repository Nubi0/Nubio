package com.chattingservice.api.chattingroom.service.Imple.ChattingRoomServiceImpl;

import com.chattingservice.api.chattingroom.dto.request.ChattingRoomEnterReq;
import com.chattingservice.api.chattingroom.dto.request.ChattingRoomOutReq;
import com.chattingservice.api.chattingroom.dto.request.ChattingRoomSearchReq;
import com.chattingservice.api.chattingroom.dto.request.GroupChattingRoomCsvReq;
import com.chattingservice.api.chattingroom.dto.response.ChattingRoomResp;
import com.chattingservice.api.chattingroom.service.ChattingRoomInfoService;
import com.chattingservice.domain.chattingroom.entity.ChattingRoom;
import com.chattingservice.domain.chattingroom.service.ChattingRoomService;
import com.chattingservice.global.error.ErrorCode;
import com.chattingservice.global.error.exception.BusinessException;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChattingRoomInfoServiceImpl implements ChattingRoomInfoService {

    private final ChattingRoomService chattingRoomService;
    private final int BATCH_SIZE = 100000; // 자를 크기를 설정

    @Transactional
    @Override
    public void roomCsvToDb(MultipartFile file) {
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

            // Use CsvToBean to map CSV records to a list of objects
            CsvToBean<GroupChattingRoomCsvReq> csvToBean = new CsvToBeanBuilder<GroupChattingRoomCsvReq>(reader)
                    .withType(GroupChattingRoomCsvReq.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<GroupChattingRoomCsvReq> parse = csvToBean.parse();
            List<ChattingRoom> chattingRooms = parse.stream()
                    .map(chattingRoomCsvReq ->
                            chattingRoomService.createGroupRoomForCsv(chattingRoomCsvReq.getSidoName()
                                    , chattingRoomCsvReq.getSggName()
                                    , chattingRoomCsvReq.getUmdName()
                                    , chattingRoomCsvReq.getRiName())
                    )
                    .collect(Collectors.toList());

            int batchSize = BATCH_SIZE; // 자를 크기를 설정
            int listSize = chattingRooms.size();

            List<List<ChattingRoom>> resultList = new ArrayList<>();

            for (int i = 0; i < listSize; i += batchSize) {
                int fromIndex = i;
                int toIndex = Math.min(i + batchSize, listSize);

                List<ChattingRoom> sublist = chattingRooms.subList(fromIndex, toIndex);
                resultList.add(sublist);
            }

            for (List<ChattingRoom> chattingRoomList : resultList) {
                chattingRoomService.createAllGroupRoom(chattingRoomList);
            }

        } catch (IOException e) {
            throw new BusinessException(ErrorCode.FILE_FORMAT);
        }

    }

    @Transactional
    @Override
    public ChattingRoomResp enterGroupRoom(ChattingRoomEnterReq chattingRoomEnterReq) {
        ChattingRoom chattingRoom = chattingRoomService.enterGroupRoom(chattingRoomEnterReq.getRoomId(), chattingRoomEnterReq.getMemberId());
        return ChattingRoomResp.form(chattingRoom);
    }

    @Transactional
    @Override
    public ChattingRoomResp outOfGroupRoom(ChattingRoomOutReq chattingRoomOutReq) {
        ChattingRoom chattingRoom = chattingRoomService.outOfGroupRoom(chattingRoomOutReq.getRoomId(), chattingRoomOutReq.getMemberId());
        return ChattingRoomResp.form(chattingRoom);
    }

    @Override
    public List<ChattingRoomResp> searchRegion(ChattingRoomSearchReq chattingRoomSearchReq) {
        List<ChattingRoom> chattingRooms = chattingRoomService.searchRegion(chattingRoomSearchReq.getSidoName()
                , chattingRoomSearchReq.getSggName(), chattingRoomSearchReq.getUmdName(), chattingRoomSearchReq.getRiName());
        return chattingRooms.stream().map(chattingRoom -> ChattingRoomResp.form(chattingRoom)).collect(Collectors.toList());
    }

    @Override
    public ChattingRoomResp findById(String roomId) {
        ChattingRoom room = chattingRoomService.findById(Long.parseLong(roomId));
        return ChattingRoomResp.form(room);
    }

    @Override
    public List<ChattingRoomResp> findMyRoomsByMemberId(String memberId) {
        List<ChattingRoom> myRoomsByMemberId = chattingRoomService.findMyRoomsByMemberId(memberId);
        return myRoomsByMemberId.stream().map(chattingRoom -> ChattingRoomResp.form(chattingRoom)).collect(Collectors.toList());
    }

}
