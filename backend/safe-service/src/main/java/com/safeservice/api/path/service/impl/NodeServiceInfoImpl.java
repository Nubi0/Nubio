package com.safeservice.api.path.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.safeservice.api.facility.dto.request.SafetyFacilityDto;
import com.safeservice.api.path.dto.request.NearNode;
import com.safeservice.api.path.dto.request.NodeDto;
import com.safeservice.api.path.service.NodeServiceInfo;
import com.safeservice.domain.facility.entity.SafetyFacility;
import com.safeservice.domain.path.entity.Node;
import com.safeservice.domain.path.service.NodeService;
import com.safeservice.global.error.ErrorCode;
import com.safeservice.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.scheduling.annotation.Async;
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
            Distance distance = new Distance(0.1, Metrics.KILOMETERS);


            List<Node> nodeList = parse.stream()
                    .map(nodeDto -> Node.builder()
                            .location(new Point(nodeDto.getLongitude(), nodeDto.getLatitude()))
//                            .safety_score(nodeService.getSafetyScore(new Point(nodeDto.getLongitude(), nodeDto.getLatitude()), distance))
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
    public List<Node> findNodeNear(NearNode nearNode) {
        Point point = new Point(nearNode.getLongitude(), nearNode.getLatitude());
        Distance distance = new Distance(nearNode.getDistance(), Metrics.KILOMETERS);
        List<Node> nodeList = nodeService.findNodeNear(point, distance);
        return nodeList;
    }
}
