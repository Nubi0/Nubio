package com.safeservice.api.path.controller;

import com.safeservice.api.ApiResponse;
import com.safeservice.api.facility.dto.request.NearSafetyFacility;
import com.safeservice.api.facility.dto.response.NearSafetyPageResponseDto;
import com.safeservice.api.path.dto.request.NearNode;
import com.safeservice.api.path.service.NodeServiceInfo;
import com.safeservice.domain.facility.constant.FacilityType;
import com.safeservice.domain.path.entity.Node;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/safe")
public class NodeController {

    private final NodeServiceInfo nodeServiceInfo;


    @PostMapping("/node/upload-csv-file")
    public ApiResponse<String> saveSafetyBell(@RequestPart("file") MultipartFile file) {
        nodeServiceInfo.registerNode(file);
        return ApiResponse.ok("저장완료");
    }

    @PostMapping("/nearwith/node")
    public ApiResponse<List<Node>> findSafeBellNearWithPaging(@Valid @RequestBody NearNode nearNode) {
        List<Node> nodeList = nodeServiceInfo.findNodeNear(nearNode);
        return ApiResponse.ok(nodeList);
    }
}
