package com.enjoyservice.domain.recomendation.mongo;

import com.enjoyservice.domain.recomendation.entity.Words;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WordsRepository extends MongoRepository<Words, String> {

}
