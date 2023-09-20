package com.safeservice.api.path.controller;

import com.safeservice.api.ApiResponse;
import com.safeservice.api.path.dto.request.NearNode;
import com.safeservice.api.path.dto.request.NodeBetweenStartAndEnd;
import com.safeservice.api.path.dto.response.NearNodeListResponse;
import com.safeservice.api.path.service.NodeServiceInfo;
import com.safeservice.domain.path.entity.Node;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/safe")
public class NodeController {

    private final NodeServiceInfo nodeServiceInfo;


    @PostMapping("/node/upload-csv-file")
    public ApiResponse<String> saveNode(@RequestPart("file") MultipartFile file) {
        nodeServiceInfo.registerNode(file);
        return ApiResponse.ok("저장완료");
    }

    @PostMapping("/nearwith/node")
    public ApiResponse<NearNodeListResponse> findNodeNear(@Valid @RequestBody NearNode nearNode) {
        NearNodeListResponse nodeList = nodeServiceInfo.findNodeNear(nearNode);
        return ApiResponse.ok(nodeList);
    }

    @PostMapping("/recommend/node")
    public ApiResponse<NearNodeListResponse > recommendNearNode(@Valid @RequestBody NodeBetweenStartAndEnd nodeBetweenStartAndEnd) {
        NearNodeListResponse nodeList = nodeServiceInfo.recommendNearNode(nodeBetweenStartAndEnd);
        return ApiResponse.ok(nodeList);
    }
}
