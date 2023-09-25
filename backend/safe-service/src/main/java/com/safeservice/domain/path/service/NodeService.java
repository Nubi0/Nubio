package com.safeservice.domain.path.service;

import com.safeservice.domain.path.entity.Node;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import java.util.List;

public interface NodeService {

    Node register(Node node);

    List<Node> saveAll(List<Node> nodeList);

    List<Node> findNodeNear(Point point, Distance distance);

    Integer getSafetyScore(Point point, Distance distance);

    Page<Node> findNodeNearPaging(Point point, Distance distance, Pageable pageable);

    List<Node> top3NodeNear(Point point, Distance distance);

}
