package com.enjoyservice.domain.node.service.impl;

import com.enjoyservice.domain.node.entity.Node;
import com.enjoyservice.domain.node.mongo.NodeRepository;
import com.enjoyservice.domain.node.service.NodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NodeServiceImpl implements NodeService {

    private final NodeRepository nodeRepository;

    @Override
    @Transactional
    public Node saveNode(Node node) {
        Node savedNode = nodeRepository.save(node);
        return savedNode;
    }

    @Override
    public Node findByCoursePk(Long coursePk) {
        Optional<Node> node = nodeRepository.findByCoursePk(coursePk);
        if (node.isEmpty()) {
            return null;
        }
        return node.get();
    }
}
