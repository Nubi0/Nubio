package com.enjoyservice.api.taste.controller;

import com.enjoyservice.api.ApiResponseEntity;
import com.enjoyservice.api.taste.dto.create.TasteInfoReq;
import com.enjoyservice.api.taste.dto.search.TasteApiRes;
import com.enjoyservice.api.taste.dto.update.MemberTasteReq;
import com.enjoyservice.api.taste.service.TasteApiService;
import com.enjoyservice.global.resolver.memberinfo.MemberInfo;
import com.enjoyservice.global.resolver.memberinfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Taste API", description = "취향 api")
@RestController
@RequestMapping("/v1/enjoy/profile")
@RequiredArgsConstructor
public class TasteController {

    private final TasteApiService tasteApiService;

    @Operation(summary = "취향 조회", description = "enjoy/v1/enjoy/profile/taste\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "OK"),
    })
    @GetMapping("/taste")
    public ApiResponseEntity<TasteApiRes> searchTaste(@MemberInfo @Parameter(hidden = true) MemberInfoDto memberInfoDto) {
        TasteApiRes tasteApiRes = tasteApiService.searchTaste(memberInfoDto.getMemberId());
        return ApiResponseEntity.ok(tasteApiRes);
    }


    @Operation(summary = "멤버 취향 생성", description = "enjoy/v1/enjoy/profile/taste\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "OK"),
    })
    @PostMapping("/taste")
    public ApiResponseEntity<String> createMemberTaste(@MemberInfo @Parameter(hidden = true) MemberInfoDto memberInfoDto,
                                                 @RequestBody MemberTasteReq memberTasteReq) {
        tasteApiService.createTaste(memberInfoDto.getMemberId(), memberTasteReq);
        return ApiResponseEntity.ok("생성 완료 하였습니다.");
    }

    @Operation(summary = "취향 생성", description = "enjoy/v1/enjoy/profile/taste/create\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "OK"),
    })
    @PostMapping("/taste/create")
    public ApiResponseEntity<String> createTaste(@MemberInfo @Parameter(hidden = true) MemberInfoDto memberInfoDto,
                                                 @RequestBody TasteInfoReq tasteInfoReq) {
        tasteApiService.saveTaste(tasteInfoReq);
        return ApiResponseEntity.ok("생성 완료 하였습니다.");
    }
}
