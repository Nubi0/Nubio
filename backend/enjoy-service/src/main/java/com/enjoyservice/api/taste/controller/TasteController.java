package com.enjoyservice.api.taste.controller;

import com.enjoyservice.api.ApiResponseEntity;
import com.enjoyservice.api.taste.dto.search.TasteApiRes;
import com.enjoyservice.api.taste.dto.update.MemberTasteReq;
import com.enjoyservice.api.taste.service.TasteApiService;
import com.enjoyservice.global.resolver.memberinfo.MemberInfo;
import com.enjoyservice.global.resolver.memberinfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/enjoy/profile")
@RequiredArgsConstructor
public class TasteController {

    private final TasteApiService tasteApiService;

    @GetMapping("/taste")
    public ApiResponseEntity<TasteApiRes> searchTaste(@MemberInfo MemberInfoDto memberInfoDto) {
        TasteApiRes tasteApiRes = tasteApiService.searchTaste(memberInfoDto.getMemberId());
        return ApiResponseEntity.ok(tasteApiRes);
    }

    @PutMapping("/taste")
    public ApiResponseEntity<String> updateTaste(@MemberInfo MemberInfoDto memberInfoDto,
                                                 @RequestBody MemberTasteReq memberTasteReq) {
        tasteApiService.updateTaste(memberInfoDto.getMemberId(), memberTasteReq);
        return ApiResponseEntity.ok("수정 완료 하였습니다.");
    }

    @PostMapping("/taste")
    public ApiResponseEntity<String> createTaste(@MemberInfo MemberInfoDto memberInfoDto,
                                                 @RequestBody MemberTasteReq memberTasteReq) {
        tasteApiService.createTaste(memberInfoDto.getMemberId(), memberTasteReq);
        return ApiResponseEntity.ok("생성 완료 하였습니다.");
    }

}
