package com.sampledomain.listener;

import com.sampledomain.messages.Message2;
import com.sampledomain.messages.Message1;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id = "listenerURLCounter", topics = "${message.topic.url}")
public class Listener {
    @KafkaHandler
    public void handleSenderMessage(Message1 msg) {
        System.out.println("URLCounter listener: Message1 received: " + msg.message());
    }

    @KafkaHandler
    public void handleReceiverMessage(Message2 msg) {
        System.out.println("URLCounter listener: Message2 received: " + msg.message());
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        System.out.println("URLCounter listener: Unknown type received: " + object);
    }
}