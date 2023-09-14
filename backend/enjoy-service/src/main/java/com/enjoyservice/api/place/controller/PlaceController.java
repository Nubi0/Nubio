package com.enjoyservice.api.place.controller;

import com.enjoyservice.api.ApiResponse;
import com.enjoyservice.api.place.dto.PlaceInfoRes;
import com.enjoyservice.api.place.service.PlaceApiService;
import com.enjoyservice.global.resolver.memberinfo.MemberInfo;
import com.enjoyservice.global.resolver.memberinfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/enjoy/place")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceApiService placeApiService;

    @GetMapping("/{placeId}")
    public ApiResponse<PlaceInfoRes> getPlaceInfo(@PathVariable Long placeId, @MemberInfo MemberInfoDto memberInfoDto) {
        PlaceInfoRes result = placeApiService.getPlaceInfo(memberInfoDto.getMemberId(), placeId);
        return ApiResponse.ok(result);
    }
}
