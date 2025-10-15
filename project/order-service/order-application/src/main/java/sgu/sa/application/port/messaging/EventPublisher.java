package sgu.sa.application.port.messaging;

import sgu.sa.core.common.DomainEvent;

import java.util.List;

public interface EventPublisher {
    void publish(DomainEvent event);
    void publishAll(List<DomainEvent> events);
}
