package sgu.sa.messaging.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import sgu.sa.application.port.messaging.EventProducer;
import sgu.sa.core.common.DomainEvent;
import sgu.sa.core.event.*;
import sgu.sa.messaging.mapper.PaymentEventMapper;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaProducer implements EventProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final PaymentEventMapper mapper;

    @Override
    public void produce(DomainEvent event) {
        Object message = switch (event) {
            case PaymentCreatedEvent e -> mapper.toAvro(e);
            case PaymentCompletedEvent e -> mapper.toAvro(e);
            case PaymentCanceledEvent e -> mapper.toAvro(e);
            case PaymentFailedEvent e -> mapper.toAvro(e);
            case PaymentExpiredEvent e -> mapper.toAvro(e);
            case PaymentRefundedEvent e -> mapper.toAvro(e);
            default -> throw new IllegalArgumentException("Unsupported event type: " + event.getClass());
        };
        kafkaTemplate.send(event.topic(), message);
    }

    @Override
    public void produceAll(List<DomainEvent> events) {
        events.forEach(this::produce);
    }
}
