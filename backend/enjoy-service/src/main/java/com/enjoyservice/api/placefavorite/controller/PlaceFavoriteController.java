package com.enjoyservice.api.placefavorite.controller;

import com.enjoyservice.api.ApiResponseEntity;
import com.enjoyservice.api.placefavorite.dto.PlaceFavoriteRes;
import com.enjoyservice.api.placefavorite.service.PlaceFavoriteApiService;
import com.enjoyservice.global.resolver.memberinfo.MemberInfo;
import com.enjoyservice.global.resolver.memberinfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "PlaceFavorite API", description = "장소 즐겨찾기 api")
@RestController
@RequestMapping("v1/enjoy/place")
@RequiredArgsConstructor
public class PlaceFavoriteController {

    private final PlaceFavoriteApiService placeFavoriteApiService;

    @Operation(summary = "장소 즐겨찾기/즐겨찾기 취소", description = "enjoy/v1/enjoy/place/favorite/{placeId}\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "OK"),
    })
    @PostMapping("/favorite/{placeId}")
    public ApiResponseEntity<PlaceFavoriteRes> courseFavorite(@PathVariable("placeId") Long placeId,
                                                              @MemberInfo @Parameter(hidden = true) MemberInfoDto memberInfoDto) {
        PlaceFavoriteRes response = placeFavoriteApiService.placeFavorite(placeId, memberInfoDto.getMemberId());
        return ApiResponseEntity.ok(response);
    }
}
