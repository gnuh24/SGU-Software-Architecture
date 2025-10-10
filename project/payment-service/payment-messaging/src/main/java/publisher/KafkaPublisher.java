package publisher;

import adapter.messaging.EventPublisher;
import common.DomainEvent;
import lombok.RequiredArgsConstructor;
import mapper.PaymentMapper;

import java.util.List;

@RequiredArgsConstructor
public class KafkaPublisher implements EventPublisher {

//    private final PaymentMapper mapper;

    @Override
    public void publish(DomainEvent event) {

    }

    @Override
    public void publishAll(List<? extends DomainEvent> events) {

    }

//    private SpecificRecord mapToAvro(DomainEvent event) {
//        return switch (event) {
//            case PaymentCreated e -> mapper.toAvro(e);
//            case PaymentCompleted e -> mapper.toAvro(e);
//            case PaymentCanceled e -> mapper.toAvro(e);
//            case PaymentExpired e -> mapper.toAvro(e);
//            case PaymentRefunded e -> mapper.toAvro(e);
//            default -> null;
//        };
//    }

}
