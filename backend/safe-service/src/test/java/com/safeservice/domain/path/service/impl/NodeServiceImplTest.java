package com.safeservice.domain.path.service.impl;

import com.safeservice.domain.facility.constant.FacilityType;
import com.safeservice.domain.facility.entity.SafetyFacility;
import com.safeservice.domain.facility.service.SafetyFacilityService;
import com.safeservice.domain.path.constant.FacilityScore;
import com.safeservice.domain.path.entity.Node;
import com.safeservice.domain.path.service.NodeService;
import com.safeservice.domain.safetybell.service.SafetyBellService;
import com.safeservice.global.error.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class NodeServiceImplTest {

    @Autowired
    NodeService nodeService;

    @Autowired
    SafetyFacilityService safetyFacilityService;

    SafetyFacility safetyFacility1;
    SafetyFacility safetyFacility2;
    SafetyFacility safetyFacility3;

    @BeforeEach
    void beforeEach() {
        safetyFacility1 = safetyFacilityService.register(SafetyFacility.builder()
                .address("안전벨")
                .location(new Point(-63.616672, -38.416097))
                .facilityType(FacilityType.SAFETY_BELL)
                .build());
        safetyFacility2 = safetyFacilityService.register(SafetyFacility.builder()
                .address("편의점")
                .location(new Point(-63.616672, -38.416097))
                .facilityType(FacilityType.CONVENIENCE_STORE)
                .build());
        safetyFacility3 = safetyFacilityService.register(SafetyFacility.builder()
                .address("가로등")
                .location(new Point(-63.616672, -38.416097))
                .facilityType(FacilityType.LAMP)
                .build());
    }

    @DisplayName("새로운 노드 등록 성공한다.")
    @Test
    void register() {

        // given
        Point point = new Point(-63.616672, -38.416097);
        Distance distance = new Distance(0.5, Metrics.KILOMETERS);
        Integer safetyScore = nodeService.getSafetyScore(point, distance);

        // when
        Node registeredNode = nodeService.register(Node.builder()
                .location(point)
                .safety_score(safetyScore)
                .build());

        // then
        assertThat(registeredNode.getSafety_score()).isEqualTo(safetyScore);
        assertThat(registeredNode.getLocation().getX()).isEqualTo(point.getX());
        assertThat(registeredNode.getLocation().getY()).isEqualTo(point.getY());

    }

    @DisplayName("500M 거리이내의 노드를 찾기 실패")
    @Test
    void findNodeNearFail() {
        // given
        Point point = new Point(-63.616672, -38.416097);
        Distance distance = new Distance(0.5, Metrics.KILOMETERS);

        // when
        // then
        assertThatThrownBy(() -> {
            nodeService.findNodeNear(point, distance);
        }).isInstanceOf(BusinessException.class);
    }


    @DisplayName("500M 거리이내의 노드를 찾기 성공")
    @Test
    void findNodeNearSuccess() {
        // given
        Point point = new Point(-63.616672, -38.416097);
        Distance distance = new Distance(0.5, Metrics.KILOMETERS);
        Integer safetyScore = nodeService.getSafetyScore(point, distance);

        // when
        Node registeredNode1 = nodeService.register(Node.builder()
                .location(point)
                .safety_score(safetyScore)
                .build());

        Node registeredNode2 = nodeService.register(Node.builder()
                .location(point)
                .safety_score(safetyScore)
                .build());

        Node registeredNode3 = nodeService.register(Node.builder()
                .location(point)
                .safety_score(safetyScore)
                .build());

        List<Node> nodeNearList = nodeService.findNodeNear(point, distance);

        // then
        assertThat(nodeNearList).hasSize(3)
                .extracting("id", "location.x", "location.y", "safety_score")
                .containsExactlyInAnyOrder(
                        tuple(registeredNode1.getId(), registeredNode1.getLocation().getX(), registeredNode1.getLocation().getY(), registeredNode1.getSafety_score()),
                        tuple(registeredNode2.getId(), registeredNode2.getLocation().getX(), registeredNode2.getLocation().getY(), registeredNode2.getSafety_score()),
                        tuple(registeredNode3.getId(), registeredNode3.getLocation().getX(), registeredNode3.getLocation().getY(), registeredNode3.getSafety_score())
                );
    }

    @DisplayName("500M 거리이내의 노드를 점수 내림차순으로 정렬 확인")
    @Test
    void findNodeNearTop() {
        // given
        Point point = new Point(-63.616672, -38.416097);
        Distance distance = new Distance(0.5, Metrics.KILOMETERS);

        // when
        Node registeredNode1 = nodeService.register(Node.builder()
                .location(point)
                .safety_score(10)
                .build());

        Node registeredNode2 = nodeService.register(Node.builder()
                .location(point)
                .safety_score(20)
                .build());

        Node registeredNode3 = nodeService.register(Node.builder()
                .location(point)
                .safety_score(30)
                .build());

        Node registeredNode4 = nodeService.register(Node.builder()
                .location(point)
                .safety_score(5)
                .build());

        List<Node> nodeNearList = nodeService.findNodeNear(point, distance);

        // then
        assertThat(nodeNearList.get(0).getSafety_score()).isEqualTo(registeredNode3.getSafety_score());
        assertThat(nodeNearList.get(1).getSafety_score()).isEqualTo(registeredNode2.getSafety_score());
        assertThat(nodeNearList.get(2).getSafety_score()).isEqualTo(registeredNode1.getSafety_score());
        assertThat(nodeNearList.get(3).getSafety_score()).isEqualTo(registeredNode4.getSafety_score());

    }

    @DisplayName("500M 거리이내의 안전시설 점수")
    @Test
    void getSafetyScore() {

        // given
        Point point = new Point(-63.616672, -38.416097);
        Distance distance = new Distance(0.5, Metrics.KILOMETERS);

        int score = FacilityScore.from(safetyFacility1.getFacilityType()).getScore()
                + FacilityScore.from(safetyFacility2.getFacilityType()).getScore()
                + FacilityScore.from(safetyFacility3.getFacilityType()).getScore();

        // when
        Integer safetyScore = nodeService.getSafetyScore(point, distance);

        // then
        assertThat(safetyScore).isEqualTo(score);
    }

    @DisplayName("500M 거리이내의 노드 안전점수 TOP3 확인")
    @Test
    void top3NodeNear() {
        // given
        Point point = new Point(-63.616672, -38.416097);
        Distance distance = new Distance(0.5, Metrics.KILOMETERS);

        // when
        Node registeredNode1 = nodeService.register(Node.builder()
                .location(point)
                .safety_score(10)
                .build());

        Node registeredNode2 = nodeService.register(Node.builder()
                .location(point)
                .safety_score(20)
                .build());

        Node registeredNode3 = nodeService.register(Node.builder()
                .location(point)
                .safety_score(30)
                .build());

        Node registeredNode4 = nodeService.register(Node.builder()
                .location(point)
                .safety_score(5)
                .build());

        List<Node> nodeNearList = nodeService.top3NodeNear(point, distance);

        // then
        assertThat(nodeNearList).hasSize(3);
        assertThat(nodeNearList.get(0).getSafety_score()).isEqualTo(registeredNode3.getSafety_score());
        assertThat(nodeNearList.get(1).getSafety_score()).isEqualTo(registeredNode2.getSafety_score());
        assertThat(nodeNearList.get(2).getSafety_score()).isEqualTo(registeredNode1.getSafety_score());

    }
}