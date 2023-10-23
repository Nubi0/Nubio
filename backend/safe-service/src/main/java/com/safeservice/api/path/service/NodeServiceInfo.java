package com.safeservice.api.path.service;

import com.safeservice.api.path.dto.request.NearNode;
import com.safeservice.api.path.dto.request.NodeBetweenStartAndEnd;
import com.safeservice.api.path.dto.response.*;
import com.safeservice.domain.path.entity.Node;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NodeServiceInfo {

    void registerNode(MultipartFile file);

    NearNodeListResponse findNodeNear(NearNode nearNode);

    RecommendNodeResponse recommendNearNode(NodeBetweenStartAndEnd nodeBetweenStartAndEnd);

    List<OsrmListResponseDto> getNearNode(NodeBetweenStartAndEnd nodeBetweenStartAndEnd);

    NearNodePageResponse findNearNodeWithPaging(NearNode nearNode, Pageable pageable);
}
