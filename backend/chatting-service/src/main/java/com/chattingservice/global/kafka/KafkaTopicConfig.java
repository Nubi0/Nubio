package com.chattingservice.global.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;
    @Value("${kafka.topic.chat-name}")
    private String topicChatName;
    @Value("${kafka.topic.room-name}")
    private String topicRoomName;

    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String,Object> configurations = new HashMap<>();
        configurations.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapAddress);
        configurations.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 120000); // 120 seconds
        configurations.put(AdminClientConfig.RETRY_BACKOFF_MS_CONFIG, 240000); // 240 seconds
        configurations.put("socket.connection.setup.timeout.ms", 30000); // 30 seconds
        configurations.put("reconnect.backoff.max.ms", 10000); // 10 seconds
        return new KafkaAdmin(configurations);
    }

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name(topicChatName)
                .partitions(1)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic roomTopic() {
        return TopicBuilder.name(topicRoomName)
                .partitions(1)
                .replicas(1)
                .build();
    }

}
