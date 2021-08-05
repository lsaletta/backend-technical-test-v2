package com.tui.proof.engine;

import com.tui.proof.domain.entity.OrderEntity;
import com.tui.proof.domain.enums.EStatusType;
import com.tui.proof.domain.repository.OrderRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Log4j2
@Component
@Getter
@Setter
@RequiredArgsConstructor
public class KafkaConsumer {

    private final OrderRepository orderRepository;
    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "1",
            containerFactory = "orderKafkaListenerContainerFactory")
    public void receive(final @Payload OrderEntity order) {
        log.info("Received order {} in the kitchen", order.getId());
        order.setStatus(EStatusType.DONE);
        orderRepository.save(order);
        latch.countDown();
    }


}
