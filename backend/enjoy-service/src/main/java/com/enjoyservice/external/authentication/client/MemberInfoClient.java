package com.enjoyservice.external.authentication.client;

import com.enjoyservice.external.authentication.dto.MemberInfoRes;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://nubi0.com/start/v1/member", name = "memberInfoClient")
public interface MemberInfoClient {

    @GetMapping(value = "/me/{identification}")
    MemberInfoRes requestMemberInfo(@PathVariable("identification") String identification);
}
