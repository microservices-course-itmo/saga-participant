package com.microservices.participant.controller;

import com.microservices.participant.KafkaClient;
import com.microservices.participant.definition.Step;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/step")
public class StepController {
    private final KafkaClient kafkaClient;
    @PostMapping(value = "", headers = {"Content-type=application/json"})
    public ResponseEntity<Step> addDefinition(@RequestBody Step stepDefinitionDto) {
        kafkaClient.produce(stepDefinitionDto);
        return ResponseEntity.ok().body(stepDefinitionDto);
    }
}
