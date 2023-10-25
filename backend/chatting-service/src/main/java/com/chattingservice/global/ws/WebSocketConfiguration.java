package com.chattingservice.global.ws;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 해당 경로로 들어오는것을 구독하는것으로 정한다.
        // /chatting/queue/room/{room_id}
        registry.enableSimpleBroker("/chatting/queue","/chatting/topic");

        // @MessageMapping("hello") 라면 경로는 -> /chatting/pub/hello
        registry.setApplicationDestinationPrefixes("/chatting/pub");
    }
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry
                .addEndpoint("/ws-chat")// ex ) ws://localhost:8080/ws-chat
                .setAllowedOrigins("*"); // 허용하는 도메인 주소 (일단 모두 허용으로 바꾸었다.)
        registry.addEndpoint("/ws-chat").setAllowedOrigins("*").withSockJS(); // 웹 소켓 사용
    }

}
