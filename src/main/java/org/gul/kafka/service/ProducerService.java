package org.gul.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ProducerService {

    @Autowired             //Key Value
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka_topic}")
    private String kafkaTopic;

    public void pushToTopic(String messageRequest) {

        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(kafkaTopic, messageRequest);

        //BiConsumer(T t, Throwable ex)
        future.whenComplete((result,ex)->{
            if(ex==null){
                System.out.println("Message sent successfully to topic "
                        + result.getRecordMetadata().topic() + " partition "
                        + result.getRecordMetadata().partition() + " offset "
                        + result.getRecordMetadata().offset());
            }else{
                System.err.println("Failed to send message: " + ex.getMessage());
            }
        });

        System.out.println(future);
    }
}
