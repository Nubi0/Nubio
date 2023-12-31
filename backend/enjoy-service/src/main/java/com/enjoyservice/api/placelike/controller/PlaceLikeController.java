package com.enjoyservice.api.placelike.controller;

import com.enjoyservice.api.ApiResponseEntity;
import com.enjoyservice.api.placelike.dto.PlaceLikeRes;
import com.enjoyservice.api.placelike.service.PlaceLikeApiService;
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

@Tag(name = "PlaceLike API", description = "장소 좋아요/좋아요 취소 api")
@RestController
@RequestMapping("v1/enjoy/place")
@RequiredArgsConstructor
public class PlaceLikeController {

    private final PlaceLikeApiService placeLikeApiService;

    @Operation(summary = "장소 좋아요/좋아요 취소", description = "enjoy/v1/enjoy/place/like/{placeId}\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "OK"),
    })
    @PostMapping("/like/{placeId}")
    public ApiResponseEntity<PlaceLikeRes> placeLike(@PathVariable Long placeId,
                                                     @MemberInfo @Parameter(hidden = true) MemberInfoDto memberInfoDto) {
        PlaceLikeRes result = placeLikeApiService.likePlace(memberInfoDto.getMemberId(), placeId);
        return ApiResponseEntity.ok(result);
    }
}
