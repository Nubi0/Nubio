package com.chattingservice.domain.chatting.mongo;

import com.chattingservice.domain.chatting.entity.MessageCollection;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class ChatMessageRepositoryImpl implements ChatMongoTemplateRepository{

    private final MongoTemplate mongoTemplate;

    /**
     최신 메시지부터 순서대로
    db.messages.find({roomId:'03f7a2d9-1ce1-4946-8186-11f50d1d6f7b'}).sort({createdAt:-1}).limit(1)
     **/
    @Override
    public MessageCollection getLastMessage(String roomId) {

        Query query = Query.query(Criteria.where("roomId").is(roomId)).with(Sort.by(Sort.Direction.DESC,"createdAt"));

        List<MessageCollection> messageCollections = mongoTemplate.find(query, MessageCollection.class);
        if (messageCollections.isEmpty()) {
            return null;
        }
        return messageCollections.get(0);
    }


    /**
     채팅방 새 메시지 받기
     let message = db.messages.findOne({_id: '6539322f96628b14393a7e9f'});
     db.messages.find({
     roomId: '03f7a2d9-1ce1-4946-8186-11f50d1d6f7b',
     createdAt: {
     '$gt': message.createdAt
     }
     }).sort({'createdAt':-1});

    **/
    @Override
    public List<MessageCollection> getNewMessages(String roomId, String readMsgId) {


        // Find the message
        MessageCollection message = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(new ObjectId(readMsgId))), MessageCollection.class);

        // Create the query for the messages
        Query query = Query.query(Criteria.where("roomId").is(roomId).and("createdAt").gt(message.getCreatedAt()));
        query.with(Sort.by(Sort.Direction.DESC, "createdAt"));

        // Execute the query
        List<MessageCollection> messageCollectionList = mongoTemplate.find(query, MessageCollection.class);


        return messageCollectionList;
    }

    /**
     해당 채팅방내 전체 메시지 가져오기
     db.messages.find({roomId : '03f7a2d9-1ce1-4946-8186-11f50d1d6f7b'}).sort({createdAt:-1})
     */
    @Override
    public List<MessageCollection> getAllMessagesAtRoom(String roomId) {
        Query query = Query.query(Criteria.where("roomId").is(roomId)).with(
                Sort.by(Sort.Direction.DESC,"createdAt"));
        return mongoTemplate.find(query,MessageCollection.class);
    }

    /**
     내림차순으로 해당 채팅방 Pagination, 사이즈 N = 50 고정
     */
    @Override
    public Page<MessageCollection> findByRoomIdWithPagingAndFiltering(String roomId, Pageable pageable) {
        Query query = new Query()
                .with(pageable) //Pageable 객체를 설정
                .skip(pageable.getPageSize() * pageable.getPageNumber()) // 건너뛰기
                .limit(pageable.getPageSize()); // 몇개 보여줄지
        query.addCriteria(Criteria.where("roomId").is(roomId));

        List<MessageCollection> messageCollections = mongoTemplate.find(query, MessageCollection.class);
        // 페이징된 결과를 생성
        Page<MessageCollection> messageCollectionPage = PageableExecutionUtils.getPage(
                messageCollections,
                pageable,
                // query.skip(-1).limit(-1)의 이유는 현재 쿼리가 페이징 하려고 하는 offset 까지만 보기에 이를 맨 처음부터 끝까지로 set 해줘 정확한 도큐먼트 개수를 구한다.
                ()-> mongoTemplate.count(query.skip(-1).limit(-1)//  모든 데이터를 대상으로 카운트
                        , MessageCollection.class)
        );

        return messageCollectionPage;
    }
}