package com.enjoyservice.api.place.controller;

import com.enjoyservice.api.ApiResponseEntity;
import com.enjoyservice.api.place.dto.PlaceInfoRes;
import com.enjoyservice.api.place.service.PlaceApiService;
import com.enjoyservice.global.resolver.memberinfo.MemberInfo;
import com.enjoyservice.global.resolver.memberinfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Place API", description = "장소 api")
@RestController
@RequestMapping("/v1/enjoy/place")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceApiService placeApiService;

    @Operation(summary = "장소 상세 조회", description = "enjoy/v1/enjoy/place/{placeId}\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "OK"),
    })
    @GetMapping("/{placeId}")
    public ApiResponseEntity<PlaceInfoRes> getPlaceInfo(@PathVariable Long placeId,
                                                        @MemberInfo @Parameter(hidden = true) MemberInfoDto memberInfoDto) {
        PlaceInfoRes result = placeApiService.getPlaceInfo(memberInfoDto.getMemberId(), placeId);
        return ApiResponseEntity.ok(result);
    }
}
