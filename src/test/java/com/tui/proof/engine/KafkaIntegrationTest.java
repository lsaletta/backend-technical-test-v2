package com.tui.proof.engine;

import com.tui.proof.common.BaseTest;
import com.tui.proof.domain.entity.OrderEntity;
import com.tui.proof.domain.enums.EStatusType;
import com.tui.proof.domain.repository.OrderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Disabled
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@ActiveProfiles("test")
public class KafkaIntegrationTest extends BaseTest {

    @Autowired
    private KafkaProducer producer;

    @Autowired
    private KafkaConsumer consumer;

    @Autowired
    OrderRepository orderRepository;

    @Value("${spring.kafka.topic}")
    private String topic;

    private List<OrderEntity> orderEntityList;

    @BeforeEach
    public void init() throws IOException {
        orderEntityList =
                getFileFromListResources("jsons/Orders.json", OrderEntity.class);
    }

    @Test
    public void givenEmbeddedKafkaBroker_whenSendOrder_thenRecivedOrder()
            throws Exception {
        OrderEntity payload = buildPayload();
        producer.send(topic, payload);
        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        Assert.assertEquals(0L, consumer.getLatch().getCount());
    }

    private OrderEntity buildPayload() {
        OrderEntity payload = orderEntityList.get(0);
        payload.setClientId(1L);
        payload.setAddressId(1L);
        payload.setStatus(EStatusType.IN_PROGRESS);
        return payload;
    }

}
