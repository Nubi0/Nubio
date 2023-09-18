package com.safeservice.api.path.service;

import com.safeservice.api.path.dto.request.NearNode;
import com.safeservice.domain.path.entity.Node;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NodeServiceInfo {

    void registerNode(MultipartFile file);

    List<Node> findNodeNear(NearNode nearNode);

}
