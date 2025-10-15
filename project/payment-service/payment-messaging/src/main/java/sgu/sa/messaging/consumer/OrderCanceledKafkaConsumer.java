package sgu.sa.messaging.consumer;

import event.avro.order.OrderCanceledAvroEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sgu.sa.application.event.ordercancel.OrderCanceledHandler;
import sgu.sa.messaging.mapper.OrderEventMapper;

@Component
@RequiredArgsConstructor
public class OrderCanceledKafkaConsumer {
    private final OrderCanceledHandler handler;
    private final OrderEventMapper mapper;

    @Transactional
    @KafkaListener(
        topics = "order-canceled",
        groupId = "order-service-group",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(OrderCanceledAvroEvent avroEvent) {
        handler.handle(mapper.toAppEvent(avroEvent));
    }
}
