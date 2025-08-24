package org.gul.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService {

    @KafkaListener(topics = "${kafka_topic}", groupId = "kafka_test_group")
    public void listenFromTopic(List<String> messagesRequest) {

        System.out.println("I am a Consumer and I have received a message from a topic and the message is: " + messagesRequest.toString());

    }
}
