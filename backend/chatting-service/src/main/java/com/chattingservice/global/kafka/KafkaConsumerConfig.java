package com.chattingservice.global.kafka;

import com.chattingservice.global.kafka.dto.request.ChatMessageDto;
import com.chattingservice.global.kafka.dto.request.RoomMessageDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;
    @Value("${spring.kafka.chat-consumer.group-id}")
    private String groupId;
    @Value("${spring.kafka.room-consumer.group-id}")
    private String rGroupId;

    @Bean
    public ConsumerFactory<String, ChatMessageDto> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfigurations(), new StringDeserializer(),
                new JsonDeserializer<>(ChatMessageDto.class));
    }

    private Map<String, Object> consumerConfigurations() {
        Map<String, Object> configurations = new HashMap<>();
        configurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configurations.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
        configurations.put(JsonDeserializer.TRUSTED_PACKAGES,"*");
        configurations.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest"); // earliest: 전체 , latest: 최신 메시지
        return configurations;
    }

    // 멀티쓰레드에 대한 동기화 제공하는 컨슈머를 생산하기 위한 Factory
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ChatMessageDto> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, ChatMessageDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, RoomMessageDto> roomConsumerFactory(){
        return new DefaultKafkaConsumerFactory<>(roomConsumerConfigurations(), new StringDeserializer(),
                new JsonDeserializer<>(RoomMessageDto.class));
    }

    private Map<String, Object> roomConsumerConfigurations() {
        Map<String, Object> configurations = new HashMap<>();
        configurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configurations.put(ConsumerConfig.GROUP_ID_CONFIG, rGroupId);
        configurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
        configurations.put(JsonDeserializer.TRUSTED_PACKAGES,"*");
        configurations.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest"); // earliest: 전체 , latest: 최신 메시지
        return configurations;
    }

}
