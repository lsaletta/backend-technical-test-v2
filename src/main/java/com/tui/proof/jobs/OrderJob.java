package com.tui.proof.jobs;

import com.tui.proof.domain.entity.OrderEntity;
import com.tui.proof.domain.enums.EStatusType;
import com.tui.proof.domain.repository.OrderRepository;
import com.tui.proof.engine.KafkaProducer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.SmartLifecycle;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@Getter
@Setter
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "com.scheduling", name = "enabled", havingValue = "true", matchIfMissing = true)
public class OrderJob implements SmartLifecycle {

    private final KafkaProducer kafkaProducer;
    private final OrderRepository orderRepository;

    @Value("${order.updateTime}")
    private Long updateTime;

    @Value("${spring.kafka.topic}")
    private String topic;

    @Scheduled(cron = "${com.scheduling.cron}")
    public void processingOrders() {
        log.info("START of processing orders");

        List<OrderEntity> orders = orderRepository.findByStatus(EStatusType.PENDING).stream()
                .filter(item -> checkDateValidation(item.getCreationDate()))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(orders)) {
            log.info("SENDING orders to cook");
            orders.stream().forEach(order -> {
                log.info("Sending order {} to kitchen", order.getId());
                order.setStatus(EStatusType.IN_PROGRESS);
                orderRepository.save(order);
                kafkaProducer.send(topic, order);
            });
        } else {
            log.info("No orders to process");
        }

        log.info("END of processing orders");
    }


    private boolean checkDateValidation(final LocalDateTime dateTime) {
        return LocalDateTime.now()
                .isAfter(dateTime.plusSeconds(updateTime));
    }


    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        // Not necessary to implement
    }

    @Override
    public int getPhase() {
        return 0;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(final Runnable arg0) {
        // Not necessary to implement
    }
}
