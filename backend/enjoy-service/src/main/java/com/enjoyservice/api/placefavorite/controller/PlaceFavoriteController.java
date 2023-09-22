package com.enjoyservice.api.placefavorite.controller;

import com.enjoyservice.api.ApiResponseEntity;
import com.enjoyservice.api.placefavorite.dto.PlaceFavoriteRes;
import com.enjoyservice.api.placefavorite.service.PlaceFavoriteApiService;
import com.enjoyservice.global.resolver.memberinfo.MemberInfo;
import com.enjoyservice.global.resolver.memberinfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/enjoy/place")
@RequiredArgsConstructor
public class PlaceFavoriteController {

    private final PlaceFavoriteApiService placeFavoriteApiService;

    @PostMapping("/favorite/{placeId}")
    public ApiResponseEntity<PlaceFavoriteRes> courseFavorite(@PathVariable("placeId") Long placeId,
                                                              @MemberInfo MemberInfoDto memberInfoDto) {
        PlaceFavoriteRes response = placeFavoriteApiService.placeFavorite(placeId, memberInfoDto.getMemberId());
        return ApiResponseEntity.ok(response);
    }
}
