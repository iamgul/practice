package org.gul.kafka.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "${kafka_topic}", groupId = "kafka_test_group")
    public void listenFromTopic(String messageRequest) {

        System.out.println("I am a Consumer and I have received a message from a topic and the message is: " + messageRequest);

    }
}
