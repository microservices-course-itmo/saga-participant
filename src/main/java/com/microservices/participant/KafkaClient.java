package com.microservices.participant;

import com.microservices.participant.definition.Step;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.Properties;

@Service
public class KafkaClient {
    /**
     * Library serialization path
     */
    private static final String SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
    private static final String EVENT_TYPE_KEY = "event-type";
    private static final String EVENT_ID_KEY = "event-id";
    private static final String SAGA_NAME_KEY = "saga-name";
    private static final String SAGA_ID_KEY = "saga-id";

    /**
     * List of kafka servers
     */
    @Value("${spring.kafka.bootstrap-server}")
    private String brokers;

    public void produce(Step step) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", brokers);
        properties.setProperty("key.serializer", SERIALIZER);
        properties.setProperty("value.serializer", SERIALIZER);
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>(step.getTopicName(), "hello");
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(step.getSagaId());
        record.headers().add(SAGA_ID_KEY, buffer.array());
        record.headers().add(SAGA_NAME_KEY, step.getSagaName().getBytes());
        record.headers().add(EVENT_ID_KEY, buffer.array());
        record.headers().add(EVENT_TYPE_KEY, step.getEventType().getBytes());
        producer.send(record);
    }
}
