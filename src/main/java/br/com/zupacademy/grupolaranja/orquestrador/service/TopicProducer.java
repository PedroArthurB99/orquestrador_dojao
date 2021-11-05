package br.com.zupacademy.grupolaranja.orquestrador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TopicProducer {

    @Value("${topic.email.producer}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message){
        System.out.println("Payload enviado: {}" +message);
        kafkaTemplate.send(topicName, message);
    }

}