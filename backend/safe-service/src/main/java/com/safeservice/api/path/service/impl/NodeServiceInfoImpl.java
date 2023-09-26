package com.safeservice.api.path.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.safeservice.api.facility.dto.request.SafetyFacilityDto;
import com.safeservice.api.path.dto.request.NearNode;
import com.safeservice.api.path.dto.request.NodeBetweenStartAndEnd;
import com.safeservice.api.path.dto.request.NodeDto;
import com.safeservice.api.path.dto.response.NearNodeListResponse;
import com.safeservice.api.path.dto.response.NearNodePageResponse;
import com.safeservice.api.path.dto.response.RecommendNodeResponse;
import com.safeservice.api.path.service.NodeServiceInfo;
import com.safeservice.domain.facility.entity.SafetyFacility;
import com.safeservice.domain.facility.service.SafetyFacilityService;
import com.safeservice.domain.path.entity.Node;
import com.safeservice.domain.path.service.NodeService;
import com.safeservice.global.error.ErrorCode;
import com.safeservice.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NodeServiceInfoImpl implements NodeServiceInfo {

    private final NodeService nodeService;
    private final SafetyFacilityService safetyFacilityService;

    private static double EARTH_RADIUS = 6371;  // 지구의 반지름 (킬로미터)

    @Override
    public void registerNode(MultipartFile file) {
        if (!StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), ".csv")) {
            throw new BusinessException(ErrorCode.INVALID_CSV_FORMAT);
        }

        try {
            CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

            // Use CsvToBean to map CSV records to a list of objects
            CsvToBean<NodeDto> csvToBean = new CsvToBeanBuilder<NodeDto>(reader)
                    .withType(NodeDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<NodeDto> parse = csvToBean.parse();
            List<Node> nodeList = parse.stream()
                    .map(nodeDto -> Node.builder()
                            .location(new Point(nodeDto.getLongitude(), nodeDto.getLatitude()))
                            .safety_score(0)
                            .build())
                    .collect(Collectors.toList());

            int batchSize = 100000; // 자를 크기를 설정
            int listSize = nodeList.size();

            List<List<Node>> resultList = new ArrayList<>();

            for (int i = 0; i < listSize; i += batchSize) {
                int fromIndex = i;
                int toIndex = Math.min(i + batchSize, listSize);

                List<Node> sublist = nodeList.subList(fromIndex, toIndex);
                resultList.add(sublist);
            }

            for (List<Node> nodes : resultList) {
                nodeService.saveAll(nodes);
            }

        } catch (IOException e) {
            throw new BusinessException(ErrorCode.FILE_FORMAT);
        }

    }

    @Override
    public NearNodeListResponse findNodeNear(NearNode nearNode) {
        Point point = new Point(nearNode.getLongitude(), nearNode.getLatitude());
        Distance distance = new Distance(nearNode.getDistance(), Metrics.KILOMETERS);
        List<Node> nodeList = nodeService.findNodeNear(point, distance);
        return NearNodeListResponse.from(nodeList);
    }

    @Override
    public RecommendNodeResponse recommendNearNode(NodeBetweenStartAndEnd nodeBetweenStartAndEnd) {
        Point point = getCenterPoint(nodeBetweenStartAndEnd.getStart_location().getLatitude(), nodeBetweenStartAndEnd.getStart_location().getLongitude()
                , nodeBetweenStartAndEnd.getEnd_location().getLatitude(), nodeBetweenStartAndEnd.getEnd_location().getLongitude());
        Distance distance = getDistance(nodeBetweenStartAndEnd.getStart_location().getLatitude(), nodeBetweenStartAndEnd.getStart_location().getLongitude()
                , nodeBetweenStartAndEnd.getEnd_location().getLatitude(), nodeBetweenStartAndEnd.getEnd_location().getLongitude());
        List<Node> nodeList = nodeService.top3NodeNear(point, distance);
        RecommendNodeResponse recommendNodeResponse = new RecommendNodeResponse();

        for (Node node : nodeList) {
            List<SafetyFacility> facilityNear = safetyFacilityService.findFacilityNear(node.getLocation(), new Distance(0.1, Metrics.KILOMETERS));
            recommendNodeResponse.add(node, facilityNear);
        }
        return recommendNodeResponse;
    }

    @Override
    public NearNodePageResponse findNearNodeWithPaging(NearNode nearNode, Pageable pageable) {
        Point point = new Point(nearNode.getLongitude(), nearNode.getLatitude());
        Distance distance = new Distance(nearNode.getDistance(), Metrics.KILOMETERS);
        Page<Node> nodeNearPaging = nodeService.findNodeNearPaging(point, distance, pageable);
        return NearNodePageResponse.from(nodeNearPaging);
    }


    private Point getCenterPoint(double start_lat, double start_lon, double end_lat, double end_lon) {
        double center_lat = (start_lat + end_lat) / (double) 2;
        double center_lon = (start_lon + end_lon) / (double) 2;
        return new Point(center_lon, center_lat);
    }

    private Distance getDistance(double startLat, double startLon, double endLat, double endLon) {
        double dLat = Math.toRadians(endLat - startLat);
        double dLon = Math.toRadians(endLon - startLon);
        double haversineSquared = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(startLat)) * Math.cos(Math.toRadians(endLat))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double angularDistance = 2 * Math.atan2(Math.sqrt(haversineSquared), Math.sqrt(1 - haversineSquared));
        double distance = EARTH_RADIUS * angularDistance / 2;
        return new Distance(distance, Metrics.KILOMETERS);
    }

}
