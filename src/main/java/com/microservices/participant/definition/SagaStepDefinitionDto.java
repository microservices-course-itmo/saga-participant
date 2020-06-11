package com.microservices.participant.definition;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SagaStepDefinitionDto {
    private String sagaName;

    private String topicName;

    private String eventType;

    private Long sagaId;
}
