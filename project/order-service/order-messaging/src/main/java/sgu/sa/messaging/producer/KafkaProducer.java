package sgu.sa.messaging.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import sgu.sa.application.port.messaging.EventProducer;
import sgu.sa.core.common.DomainEvent;
import sgu.sa.core.event.OrderCanceledEvent;
import sgu.sa.core.event.OrderCreatedEvent;
import sgu.sa.core.event.OrderPaidEvent;
import sgu.sa.messaging.mapper.OrderEventMapper;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaProducer implements EventProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final OrderEventMapper mapper;

    @Override
    public void produce(DomainEvent event) {
        Object message = switch (event) {
            case OrderCreatedEvent e -> mapper.toAvro(e);
            case OrderCanceledEvent e -> mapper.toAvro(e);
            case OrderPaidEvent e -> mapper.toAvro(e);
            default -> throw new IllegalArgumentException("Unsupported event type: " + event.getClass());
        };
        kafkaTemplate.send(event.topic(), message);
    }

    @Override
    public void produceAll(List<DomainEvent> events) {
        events.forEach(this::produce);
    }
}
