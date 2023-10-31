package com.chattingservice.domain.room.mongo;

import com.chattingservice.domain.room.dto.request.ReqDmDto;
import com.chattingservice.domain.room.dto.request.ReqInviteDto;
import com.chattingservice.domain.room.dto.request.ReqReadMessage;
import com.chattingservice.domain.room.entity.RoomCollection;
import com.mongodb.client.result.UpdateResult;

import java.util.List;

public interface RoomMongoTemplateRepository {

    UpdateResult enterRoom(String roomId, String userId);

    boolean isMyRoom(String roomId, String userId);

    UpdateResult inviteMembers(ReqInviteDto reqInviteDto);

    List<RoomCollection> findMyRoomsByUserId(String userId);

    UpdateResult outOfTheRoom(String roomId, String userId);

    RoomCollection matchDmMembers(ReqDmDto reqDmDto);

    UpdateResult updateLastReadMsgId(ReqReadMessage reqReadMessage);

}
