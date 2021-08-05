package com.tui.proof.engine;

import com.tui.proof.domain.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, OrderEntity> kafkaTemplate;

    public void send(final String topic, final OrderEntity payload) {
        kafkaTemplate.send(topic, payload);
    }

}
