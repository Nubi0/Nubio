package com.safeservice.domain.path.service.impl;

import com.safeservice.domain.facility.entity.SafetyFacility;
import com.safeservice.domain.facility.service.SafetyFacilityService;
import com.safeservice.domain.path.constant.FacilityScore;
import com.safeservice.domain.path.entity.Node;
import com.safeservice.domain.path.mongo.NodeRepository;
import com.safeservice.domain.path.service.NodeService;
import com.safeservice.global.error.ErrorCode;
import com.safeservice.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NodeServiceImpl implements NodeService {

    private final NodeRepository nodeRepository;
    private final SafetyFacilityService safetyFacilityService;

    @Override
    @Transactional
    public Node register(Node node) {
        return nodeRepository.save(node);
    }

    @Override
    @Transactional
    public List<Node> saveAll(List<Node> nodeList) {
        return nodeRepository.saveAll(nodeList);
    }

    @Override
    public List<Node> findNodeNear(Point point, Distance distance) {
        Sort sort = Sort.by(Sort.Direction.DESC, "safety_score");
        List<Node> findNodeList = nodeRepository.findNodeByLocationNear(point, distance, sort);
        if (findNodeList.size() == 0) {
            throw new BusinessException(ErrorCode.FACILITY__NOT_EXIST);
        }
        return findNodeList;
    }

    @Override
    public Integer getSafetyScore(Point point, Distance distance) {

        List<SafetyFacility> facilityNear = safetyFacilityService.findFacilityNear(point, distance);

        int score = facilityNear.stream()
                .mapToInt(safetyFacility -> FacilityScore.from(safetyFacility.getFacilityType()).getScore())
                .sum();

        return score;
    }

    @Override
    public Page<Node> findNodeNearPaging(Point point, Distance distance, Pageable pageable) {
        return nodeRepository.findNodeByLocationNear(point, distance, pageable);
    }

    @Override
    public List<Node> top3NodeNear(Point point, Distance distance) {
        Sort sort = Sort.by(Sort.Direction.DESC, "safety_score");
        List<Node> nodeByLocationNear = nodeRepository.findNodeByLocationNear(point, distance, sort);

        int fromIndex = 0;
        int toIndex = Math.min(3, nodeByLocationNear.size());

        return nodeByLocationNear.subList(fromIndex, toIndex);
    }
}
