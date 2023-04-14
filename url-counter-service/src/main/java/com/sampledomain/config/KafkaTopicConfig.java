package com.sampledomain.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Value(value = "${message.topic.url}")
    private String topicUrl;

    @Bean
    public NewTopic topicUrl(){
        return TopicBuilder.name(topicUrl).build();
        //return new NewTopic(topicUrl, 1, (short) 1);
    }
}
