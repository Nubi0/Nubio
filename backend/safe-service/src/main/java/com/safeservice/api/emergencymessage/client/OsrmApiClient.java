package com.safeservice.api.emergencymessage.client;

import com.safeservice.api.path.dto.response.OsrmDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "http://ec2-3-39-119-234.ap-northeast-2.compute.amazonaws.com:5000/route/v1/foot/", name = "OsrmClient")
public interface OsrmApiClient {

    @RequestMapping(method = RequestMethod.GET, value = "{coordinates}?steps=true")
    ResponseEntity<OsrmDto> getRoute(@PathVariable String coordinates);

}
