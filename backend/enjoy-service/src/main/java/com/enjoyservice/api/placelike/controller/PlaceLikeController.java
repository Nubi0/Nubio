package com.enjoyservice.api.placelike.controller;

import com.enjoyservice.api.ApiResponseEntity;
import com.enjoyservice.api.placelike.dto.PlaceLikeRes;
import com.enjoyservice.api.placelike.service.PlaceLikeApiService;
import com.enjoyservice.global.resolver.memberinfo.MemberInfo;
import com.enjoyservice.global.resolver.memberinfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/enjoy/place")
@RequiredArgsConstructor
public class PlaceLikeController {

    private final PlaceLikeApiService placeLikeApiService;

    @PostMapping("/like/{placeId}")
    public ApiResponseEntity<PlaceLikeRes> placeLike(@PathVariable Long placeId, @MemberInfo MemberInfoDto memberInfoDto) {
        PlaceLikeRes result = placeLikeApiService.likePlace(memberInfoDto.getMemberId(), placeId);
        return ApiResponseEntity.ok(result);
    }
}
