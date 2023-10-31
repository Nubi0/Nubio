package com.chattingservice.domain.chatting.mongo;

import com.chattingservice.domain.chatting.entity.MessageCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChatMongoTemplateRepository {

    MessageCollection getLastMessage(String roomId);
    List<MessageCollection> getNewMessages(String roomId, String readMsgId);
    List<MessageCollection> getAllMessagesAtRoom(String roomId);
    Page<MessageCollection> findByRoomIdWithPagingAndFiltering(String roomId, Pageable page);

}
