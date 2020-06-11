package com.microservices.participant.controller;

import com.microservices.participant.KafkaClient;
import com.microservices.participant.definition.SagaStepDefinitionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/definition")
public class DefinitionController {
    private final KafkaClient kafkaClient;
    @PostMapping(value = "", headers = {"Content-type=application/json"})
    public ResponseEntity<SagaStepDefinitionDto> addDefinition(@RequestBody SagaStepDefinitionDto stepDefinitionDto) {
        kafkaClient.produce(stepDefinitionDto);
        return ResponseEntity.ok().body(stepDefinitionDto);
    }
}
