package org.gul.kafka.controller;

import org.gul.kafka.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class KafkaController {

    @Autowired
    private ProducerService producerService;

    @PostMapping(value = "/produce", consumes = "application/json")
    public void produceMessageToTopic(@RequestBody List<String> messages){
        producerService.pushToTopic(messages);
    }
}
