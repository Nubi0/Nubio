package com.safeservice.api.path.controller;

import com.safeservice.api.ApiResponseEntity;
import com.safeservice.api.path.dto.request.NearNode;
import com.safeservice.api.path.dto.request.NodeBetweenStartAndEnd;
import com.safeservice.api.path.dto.response.*;
import com.safeservice.api.path.service.NodeServiceInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Node API", description = "안전 노드 api")
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

    @Operation(summary = "출발지와 도착지 사이 안전한 노드 조회", description = "safe/v1/safe/recommend/node\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/recommend/node")
    public ApiResponseEntity<RecommendNodeResponse > recommendNearNode(@Valid @RequestBody NodeBetweenStartAndEnd nodeBetweenStartAndEnd) {
        RecommendNodeResponse nodeList = nodeServiceInfo.recommendNearNode(nodeBetweenStartAndEnd);
        return ApiResponseEntity.ok(nodeList);
    }

    @Operation(summary = "출발지와 도착지 사이 안전한 노드 조회", description = "safe/v1/safe/route/node\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/route/node")
    public ApiResponseEntity<List<OsrmListResponseDto> > getNearNode(@Valid @RequestBody NodeBetweenStartAndEnd nodeBetweenStartAndEnd) {
        List<OsrmListResponseDto> osrmListResponseDto = nodeServiceInfo.getNearNode(nodeBetweenStartAndEnd);
        return ApiResponseEntity.ok(osrmListResponseDto);
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
