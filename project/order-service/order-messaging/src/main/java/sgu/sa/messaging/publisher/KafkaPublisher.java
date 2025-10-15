package sgu.sa.messaging.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sgu.sa.application.port.messaging.EventPublisher;
import sgu.sa.core.common.DomainEvent;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaPublisher implements EventPublisher {
    @Override
    public void publish(DomainEvent event) {

    }

    @Override
    public void publishAll(List<DomainEvent> events) {

    }
}
