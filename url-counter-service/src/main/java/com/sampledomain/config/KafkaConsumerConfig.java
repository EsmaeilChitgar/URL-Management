package com.sampledomain.config;

import com.sampledomain.messages.Message1;
import com.sampledomain.messages.Message2;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public RecordMessageConverter converter() {
        StringJsonMessageConverter converter = new StringJsonMessageConverter();
//        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
//        typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);
//        typeMapper.addTrustedPackages("com.example.kafka");
//        Map<String, Class<?>> mappings = new HashMap<>();
//        mappings.put("message1", Message1.class);
//        mappings.put("message2", Message2.class);
//        typeMapper.setIdClassMapping(mappings);
//        converter.setTypeMapper(typeMapper);
        return converter;
    }

    public Map<String, Object> consumerConfig(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        //props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.kafka");

//        //props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, "20971520");
//        props.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, "20971520");
//        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
//
//        props.put(JsonSerializer.TYPE_MAPPINGS, "message1:com.example.Kafka.Sender.messages.Message1, message2:com.example.Kafka.Sender.messages.Message2");
//        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.example.Kafka.Sender.messages.Message1");
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, Message1> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(),
                new JsonDeserializer<>(Message1.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Message1> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Message1> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        //factory.setMessageConverter(converter());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Message2> consumerFactory2() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(),
                new JsonDeserializer<>(Message2.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Message2> kafkaListenerContainerFactory2() {
        ConcurrentKafkaListenerContainerFactory<String, Message2> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory2());
        //factory.setMessageConverter(converter());
        return factory;
    }
}
