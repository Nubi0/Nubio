package com.safeservice.domain.path.mongo;

import com.safeservice.domain.path.entity.Node;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NodeRepository extends MongoRepository<Node, String> {

    List<Node> findNodeByLocationNear(Point point, Distance distance);

    List<Node> findNodeByLocationNear(Point point, Distance distance, Sort sort);

    Page<Node> findNodeByLocationNear(Point point, Distance distance, Pageable pageable);

}
