package com.rimdev.gateway.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

import com.rimdev.gateway.entities.Device;
 
@Configuration
public class KafkaConfig {
 
    @Value("${kafka.group.id}")
    private String groupId;
 
    @Value("${kafka.reply.topic}")
    private String replyTopic;
 
    @Bean
    public ReplyingKafkaTemplate<String, Device, Device> replyingKafkaTemplate(ProducerFactory<String, Device> pf,
            ConcurrentKafkaListenerContainerFactory<String, Device> factory) {
        ConcurrentMessageListenerContainer<String, Device> replyContainer = factory.createContainer(replyTopic);
        replyContainer.getContainerProperties().setMissingTopicsFatal(false);
        replyContainer.getContainerProperties().setGroupId(groupId);
        return new ReplyingKafkaTemplate<>(pf, replyContainer);
    }
 
    @Bean
    public KafkaTemplate<String, Device> replyTemplate(ProducerFactory<String, Device> pf,
            ConcurrentKafkaListenerContainerFactory<String, Device> factory) {
        KafkaTemplate<String, Device> kafkaTemplate = new KafkaTemplate<>(pf);
        factory.getContainerProperties().setMissingTopicsFatal(false);
        factory.setReplyTemplate(kafkaTemplate);
        return kafkaTemplate;
    }
}