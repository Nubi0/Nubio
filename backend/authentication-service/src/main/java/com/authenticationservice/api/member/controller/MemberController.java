package com.authenticationservice.api.member.controller;

import com.authenticationservice.api.ApiResponseEntity;
import com.authenticationservice.api.member.dto.request.NicknameCheckDto;
import com.authenticationservice.api.member.dto.response.MemberResDto;
import com.authenticationservice.api.member.service.MemberInfoService;
import com.authenticationservice.global.resolver.memberInfo.MemberInfo;
import com.authenticationservice.global.resolver.memberInfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
@Tag(name = "4. Member API", description = "회원 api")
public class MemberController {

    final MemberInfoService memberInfoService;

    @Operation(summary = "프로필 조회", description = "auth/v1/member/me\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "OK"),
            @ApiResponse(responseCode = "A-001", description =  "TOKEN_EXPIRED", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"A-001\", \"errorMessage\": \"토큰이 만료되었습니다.\"}"))),
            @ApiResponse(responseCode = "A-002", description =  "NOT_VALID_TOKEN", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"A-002\", \"errorMessage\": \"해당 토큰은 유효한 토큰이 아닙니다.\"}"))),
            @ApiResponse(responseCode = "M-007", description =  "MEMBER_NOT_EXISTS", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-007\", \"errorMessage\": \"해당 회원은 존재하지 않습니다.\"}"))),
    })
    @GetMapping("/me")
    public ApiResponseEntity<MemberResDto> info(@MemberInfo MemberInfoDto memberInfo) {
        return ApiResponseEntity.ok(memberInfoService.getMemberInfo(memberInfo));
    }

    @Operation(summary = "identification으로 조회", description = "start/v1/member/me/{identification}\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "OK"),
            @ApiResponse(responseCode = "M-007", description =  "MEMBER_NOT_EXISTS", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-007\", \"errorMessage\": \"해당 회원은 존재하지 않습니다.\"}"))),
    })
    @GetMapping("/me/{identification}")
    public ApiResponseEntity<MemberResDto> info(@PathVariable(name = "identification") String identification) {

        return ApiResponseEntity.ok(memberInfoService.getMemberByIdentification(identification));
    }

    @Operation(summary = "닉네임 중복 확인", description = "/start/v1/member/nickname\n\n 닉네임 사용 가능 시 true 반환 \n\n 닉네임 중복 시 false 반환" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "OK"),
    })
    @PostMapping("/nickname")
    public ApiResponseEntity<Boolean> checkNickname(@Valid @RequestBody NicknameCheckDto nicknameCheckDto) {
        return ApiResponseEntity.ok(memberInfoService.checkNickname(nicknameCheckDto));
    }

    @Operation(summary = "프로필 수정", description = "auth/v1/member/me\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description =  "CREATED"),
            @ApiResponse(responseCode = "A-001", description =  "TOKEN_EXPIRED", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"A-001\", \"errorMessage\": \"토큰이 만료되었습니다.\"}"))),
            @ApiResponse(responseCode = "A-002", description =  "NOT_VALID_TOKEN", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"A-002\", \"errorMessage\": \"해당 토큰은 유효한 토큰이 아닙니다.\"}"))),
            @ApiResponse(responseCode = "M-004", description =  "INVALID_GENDER", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-004\", \"errorMessage\": \"성별 타입이 잘못됐습니다.\"}"))),
            @ApiResponse(responseCode = "M-005", description =  "INVALID_BIRTH_FORMAT", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-005\", \"errorMessage\": \"생년월일 형식이 잘못됐습니다.\"}"))),
            @ApiResponse(responseCode = "M-007", description =  "MEMBER_NOT_EXISTS", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-007\", \"errorMessage\": \"해당 회원은 존재하지 않습니다.\"}"))),
    })
    @PatchMapping("/me")
    public ApiResponseEntity<String> updateMe(@MemberInfo MemberInfoDto memberInfo,
                                              @RequestPart(name = "profileUrl", required = false) MultipartFile profileImg,
                                              @RequestPart(name = "nickname", required = false) String nickName,
                                              @RequestPart(name = "gender", required = false) String gender,
                                              @RequestPart(name = "birth", required = false) String birth) {
        memberInfoService.updateMemberInfo(memberInfo, profileImg, nickName, gender, birth);
        return ApiResponseEntity.ok("Success");
    }

    @Operation(summary = "회원 탈퇴", description = "auth/v1/member/delete\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "OK"),
            @ApiResponse(responseCode = "A-001", description =  "TOKEN_EXPIRED", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"A-001\", \"errorMessage\": \"토큰이 만료되었습니다.\"}"))),
            @ApiResponse(responseCode = "A-002", description =  "NOT_VALID_TOKEN", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"A-002\", \"errorMessage\": \"해당 토큰은 유효한 토큰이 아닙니다.\"}"))),
    })
    @PatchMapping("/delete")
    public ApiResponseEntity<String> deleteMe(@MemberInfo MemberInfoDto memberInfo) {
        memberInfoService.deleteMember(memberInfo);
        return ApiResponseEntity.ok("Success");
    }
}
