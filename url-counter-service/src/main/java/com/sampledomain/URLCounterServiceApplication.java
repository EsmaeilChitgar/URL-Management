package com.sampledomain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class URLCounterServiceApplication {
	//	@Value(value = "${message.topic.name}")
	//	private String topic;

		//	@Bean
	//	CommandLineRunner commandLineRunner(KafkaTemplate<String, SenderMessage> kafkaTemplate) {
	//		return args -> {
	//			kafkaTemplate.send(topic, new SenderMessage("Hello From Kafka Receiver", LocalDateTime.now()));
	//		};
	//	}
	public static void main(String[] args) {
		SpringApplication.run(URLCounterServiceApplication.class, args);
	}
}
