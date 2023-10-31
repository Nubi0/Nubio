package com.chattingservice.domain.room.mongo;

import com.chattingservice.domain.room.entity.RoomCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends MongoRepository<RoomCollection,String>, RoomMongoTemplateRepository{

    Optional<RoomCollection> findByRoomId(String roomId);

    boolean existsByRoomId(String roomId);

}
