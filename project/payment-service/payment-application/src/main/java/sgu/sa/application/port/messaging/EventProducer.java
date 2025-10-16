package sgu.sa.application.port.messaging;

import sgu.sa.core.common.DomainEvent;

import java.util.List;


public interface EventProducer {
    void produce(DomainEvent event);
    void produceAll(List<DomainEvent> events);
}
