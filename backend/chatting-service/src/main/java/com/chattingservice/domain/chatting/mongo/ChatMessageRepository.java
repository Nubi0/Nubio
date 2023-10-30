package com.chattingservice.domain.chatting.mongo;

import com.chattingservice.domain.chatting.entity.MessageCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<MessageCollection, String>, ChatMongoTemplateRepository {
}
