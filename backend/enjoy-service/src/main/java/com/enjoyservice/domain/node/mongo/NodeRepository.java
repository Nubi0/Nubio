package com.enjoyservice.domain.node.mongo;

import com.enjoyservice.domain.node.entity.Node;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NodeRepository extends MongoRepository<Node, String> {
    Optional<Node> findByCoursePk(Long coursePk);
}
