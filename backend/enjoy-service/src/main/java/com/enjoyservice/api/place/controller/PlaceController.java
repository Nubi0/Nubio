package com.enjoyservice.api.place.controller;

import com.enjoyservice.api.ApiResponseEntity;
import com.enjoyservice.api.place.dto.NearPlaceInfoPageRes;
import com.enjoyservice.api.place.dto.NearPlaceReq;
import com.enjoyservice.api.place.dto.PlaceInfoRes;
import com.enjoyservice.api.place.service.PlaceApiService;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.entity.constant.GroupCode;
import com.enjoyservice.global.resolver.memberinfo.MemberInfo;
import com.enjoyservice.global.resolver.memberinfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Place API", description = "장소 api")
@RestController
@RequestMapping("/v1/enjoy/place")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceApiService placeApiService;

    @Operation(summary = "장소 상세 조회", description = "enjoy/v1/enjoy/place/{placeId}\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @GetMapping("/{placeId}")
    public ApiResponseEntity<PlaceInfoRes> getPlaceInfo(@PathVariable Long placeId,
                                                        @MemberInfo @Parameter(hidden = true) MemberInfoDto memberInfoDto) {
        PlaceInfoRes result = placeApiService.getPlaceInfo(memberInfoDto.getMemberId(), placeId);
        return ApiResponseEntity.ok(result);
    }


    @Operation(summary = "근처의 장소 조회", description = "enjoy/v1/enjoy/place/nearwith/enjoy-place\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/nearwith/enjoy-place")
    public ApiResponseEntity<NearPlaceInfoPageRes> findNearPlaceType(
            @RequestParam("longitude") double longitude,
            @RequestParam("latitude") double latitude,
            @RequestParam("distance") int distance,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "name", required = false) String name,
            @PageableDefault(size = 50,
                    sort = "id",
                    direction = Sort.Direction.ASC) Pageable pageable) {
        NearPlaceReq nearPlaceReq = NearPlaceReq.builder()
                .longitude(longitude)
                .latitude(latitude)
                .distance(distance)
                .build();
        NearPlaceInfoPageRes nearPlace = placeApiService.searchNearPlace(nearPlaceReq, pageable, category, name);
        return ApiResponseEntity.ok(nearPlace);
    }


}
