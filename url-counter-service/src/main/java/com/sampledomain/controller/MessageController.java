package com.sampledomain.controller;

import com.sampledomain.messages.MessageRequest;
import com.sampledomain.messages.Message2;
import com.sampledomain.messages.Message1;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/urls")
public class MessageController {
    @Value("${message.topic.url}")
    private String topicUrl;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public MessageController(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public void publish(@RequestBody MessageRequest request) {
        Message1 message = new Message1(request.message(), LocalDateTime.now());
        Message2 message2 = new Message2(request.message(), LocalDateTime.now());

        System.out.println("message: " + message);

        kafkaTemplate.send(topicUrl, message);
        kafkaTemplate.send(topicUrl, message2);
    }
 }
