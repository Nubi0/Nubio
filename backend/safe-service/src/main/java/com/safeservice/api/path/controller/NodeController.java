package com.safeservice.api.path.controller;

import com.safeservice.api.ApiResponseEntity;
import com.safeservice.api.path.dto.request.NearNode;
import com.safeservice.api.path.dto.request.NodeBetweenStartAndEnd;
import com.safeservice.api.path.dto.response.NearNodeListResponse;
import com.safeservice.api.path.dto.response.NearNodePageResponse;
import com.safeservice.api.path.service.NodeServiceInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/safe")
public class NodeController {

    private final NodeServiceInfo nodeServiceInfo;


    @PostMapping("/node/upload-csv-file")
    public ApiResponseEntity<String> saveNode(@RequestPart("file") MultipartFile file) {
        nodeServiceInfo.registerNode(file);
        return ApiResponseEntity.ok("저장완료");
    }

    @PostMapping("/nearwith/node")
    public ApiResponseEntity<NearNodeListResponse> findNodeNear(@Valid @RequestBody NearNode nearNode) {
        NearNodeListResponse nodeList = nodeServiceInfo.findNodeNear(nearNode);
        return ApiResponseEntity.ok(nodeList);
    }

    @PostMapping("/recommend/node")
    public ApiResponseEntity<NearNodeListResponse > recommendNearNode(@Valid @RequestBody NodeBetweenStartAndEnd nodeBetweenStartAndEnd) {
        NearNodeListResponse nodeList = nodeServiceInfo.recommendNearNode(nodeBetweenStartAndEnd);
        return ApiResponseEntity.ok(nodeList);
    }


    @PostMapping("/nearwith-page/node")
    public ApiResponseEntity<NearNodePageResponse> findNodeNearWithPaging(@Valid @RequestBody NearNode nearNode
    , @PageableDefault(size = 20,
            sort = "safety_score",
            direction = Sort.Direction.DESC) Pageable pageable) {
        NearNodePageResponse nearNodeWithPaging = nodeServiceInfo.findNearNodeWithPaging(nearNode, pageable);
        return ApiResponseEntity.ok(nearNodeWithPaging);
    }

}
