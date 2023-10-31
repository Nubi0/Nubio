package com.chattingservice.domain.room.mongo;

import com.chattingservice.domain.room.dto.request.ReqDmDto;
import com.chattingservice.domain.room.dto.request.ReqInviteDto;
import com.chattingservice.domain.room.dto.request.ReqReadMessage;
import com.chattingservice.domain.room.entity.Member;
import com.chattingservice.domain.room.entity.RoomCollection;
import com.chattingservice.global.error.ErrorCode;
import com.chattingservice.global.error.exception.BusinessException;
import com.chattingservice.global.kafka.dto.request.RoomType;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class RoomRepositoryImpl implements RoomMongoTemplateRepository{

    private final MongoTemplate mongoTemplate;


    /**
     멤버 초대
     */
    @Override
    public UpdateResult inviteMembers(ReqInviteDto reqInviteDto) {
        RoomCollection rc = mongoTemplate.findOne(Query.query(Criteria.where("roomId").is(reqInviteDto.getRoom_id())), RoomCollection.class);
        if(rc == null){
            throw new BusinessException(ErrorCode.ROOM_NOT_EXIST);
        }
        List<String> members = new ArrayList<>();
        reqInviteDto.getMembers().forEach(userid->{
            if (!isMyRoom(reqInviteDto.getRoom_id(), userid)) {
                members.add(userid);
            }
        });

        Query query = Query.query(Criteria.where("roomId").is(reqInviteDto.getRoom_id()));
        Update update = new Update().push("members").each(convertToMembersList(members));
        update.set("title",rc.getTitle()+","+String.join(",",reqInviteDto.getMembers()));
        return mongoTemplate.updateFirst(query, update, RoomCollection.class);
    }

    /**
     채팅방 입장
     */
    @Override
    public UpdateResult enterRoom(String roomId, String userId) {
        RoomCollection rc = mongoTemplate.findOne(Query.query(Criteria.where("roomId").is(roomId)), RoomCollection.class);
        if(rc == null){
            throw new BusinessException(ErrorCode.ROOM_NOT_EXIST);
        }
        Query query = Query.query(Criteria.where("roomId").is(rc));
        Member member = new Member(userId, LocalDateTime.now());
        Update update = new Update().push("members",member);
        return mongoTemplate.updateFirst(query, update, RoomCollection.class);
    }

    /**
     자신이 속한 채팅방 조회
     */
    @Override
    public List<RoomCollection> findMyRoomsByUserId(String userId) {
        Query query = Query.query(Criteria.where("members").elemMatch(
                Criteria.where("userId").is(userId)
        ));

        return mongoTemplate.find(query,RoomCollection.class);
    }

    /**
     자기가 속한 방인지 확인
     */
    @Override
    public boolean isMyRoom(String roomId, String userId) {

        Query query = Query.query(Criteria.where("roomId").is(roomId).and("members.userId").is(userId));

        return mongoTemplate.exists(query, RoomCollection.class);
    }

    /**
     채팅방 나가기
     */
    @Override
    public UpdateResult outOfTheRoom(String roomId, String userId) {
        Query query = Query.query(Criteria.where("roomId").is(roomId));

        Update update = new Update();
        update.pull("members", new Query(Criteria.where("userId").in(userId)) );

        return mongoTemplate.updateFirst(query,update,"rooms");
    }

    /**
     이미 개설된 1:1 채팅방 있는지 조회
     */
    @Override
    public RoomCollection matchDmMembers(ReqDmDto reqDmDto) {
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("type").is(RoomType.DM),
                Criteria.where("members").elemMatch(Criteria.where("userId").is(reqDmDto.getCreator())),
                Criteria.where("members").elemMatch(Criteria.where("userId").is(reqDmDto.getMessage_to())));

        return mongoTemplate.findOne(new Query(criteria),RoomCollection.class);
    }

    /**
     * 마지막 읽었던 채팅 메시지 id 업데이트
     db.rooms.updateOne({'roomId':'03f7a2d9-1ce1-4946-8186-11f50d1d6f7b','members.userId':'1'},{$set:{'members.$.lastReadMsgId':'653928ddec30e36c8a453203'}} )
     */
    @Override
    public UpdateResult updateLastReadMsgId(ReqReadMessage reqReadMessage) {
        Query query = Query.query(Criteria.where("roomId").is(reqReadMessage.getRoom_id())
                .andOperator(Criteria.where("members").elemMatch(Criteria.where("userId").is(reqReadMessage.getUser_id())))
        );
        // members는 객체의 배열이고, 쿼리 조건과 일치하는 배열 $의 요소를 참조하는 위치 연산자
        Update update = new Update().set("members.$.lastReadMsgId",reqReadMessage.getMessage_id());

        return mongoTemplate.updateFirst(query,update,"rooms");
    }


    public List<Member> convertToMembersList(List<String>list){
        List<Member> members = new ArrayList<>();
        list.forEach(s -> members.add(new Member(s, LocalDateTime.now())));
        return members;
    }
}
