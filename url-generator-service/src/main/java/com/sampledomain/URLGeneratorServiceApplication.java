package com.sampledomain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class URLGeneratorServiceApplication {
//	@Value(value = "${message.topic.name2}")
//	private String topic2;
//
//		@Bean
//	CommandLineRunner commandLineRunner(KafkaTemplate<String, SenderMessage> kafkaTemplate) {
//        return args -> {
//            kafkaTemplate.send(topic2, new SenderMessage("Hello From Kafka sender to topic2", LocalDateTime.now()));
//        };
//    }

	public static void main(String[] args) {
		SpringApplication.run(URLGeneratorServiceApplication.class, args);
	}
}
