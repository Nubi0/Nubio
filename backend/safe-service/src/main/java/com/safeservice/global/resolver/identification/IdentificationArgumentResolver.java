package com.safeservice.global.resolver.identification;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class IdentificationArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasMemberInfoAnnotation = parameter.hasParameterAnnotation(Identification.class);
        boolean hasMemberInfoDto = IdentificationDto.class.isAssignableFrom(parameter.getParameterType());
        return hasMemberInfoAnnotation && hasMemberInfoDto;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/jwt/claim")
                .queryParam("Authorization", request.getHeader("Authorization"))
                .encode()
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> forEntity = restTemplate.getForEntity(uri, Map.class);

//        String memberId = request.getHeader("X-Identification");
//        String role = request.getHeader("X-Role");

        return IdentificationDto.builder()
                .identification( (String)forEntity.getBody().get("identification"))
                .role((String)forEntity.getBody().get("role")).build();
    }
}
