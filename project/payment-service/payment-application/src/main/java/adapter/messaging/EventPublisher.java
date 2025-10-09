package adapter.messaging;

import common.DomainEvent;

import java.util.List;

public interface EventPublisher {
    void publish(DomainEvent event);
    void publishAll(List<? extends DomainEvent> events);
}
