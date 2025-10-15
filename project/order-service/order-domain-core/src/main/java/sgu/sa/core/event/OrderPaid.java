package sgu.sa.core.event;

import sgu.sa.core.common.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record OrderPaid(
    UUID orderId,
    Instant occurredOn
) implements DomainEvent {
}
