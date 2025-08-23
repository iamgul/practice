package org.gul.kafka.controller;

import org.gul.kafka.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class KafkaController {

    @Autowired
    private ProducerService producerService;

    @GetMapping("/produce")
    public void produceMessageToTopic(@RequestParam("message") String message){
        producerService.pushToTopic(message);
    }
}
