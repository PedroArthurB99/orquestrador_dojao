package br.com.zupacademy.grupolaranja.orquestrador.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailTopicProducer {

    @Value("${topic.email.producer}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public EmailTopicProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String message){
        System.out.println("Payload enviado: {"+message+"}");
        kafkaTemplate.send(topicName, message);
    }

}