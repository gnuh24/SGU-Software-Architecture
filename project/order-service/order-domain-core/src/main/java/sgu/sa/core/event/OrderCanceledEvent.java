package sgu.sa.core.event;

import sgu.sa.core.common.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record OrderCanceledEvent(
    UUID orderId,
    Instant occurredOn
) implements DomainEvent {
    @Override
    public String topic() {
        return "order-canceled";
    }
}
