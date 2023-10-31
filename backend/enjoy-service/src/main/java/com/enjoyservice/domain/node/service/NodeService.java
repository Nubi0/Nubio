package com.enjoyservice.domain.node.service;

import com.enjoyservice.domain.node.entity.Node;

import java.util.Optional;

public interface NodeService {
    Node saveNode(Node node);
    Node findByCoursePk(Long coursePk);
}
