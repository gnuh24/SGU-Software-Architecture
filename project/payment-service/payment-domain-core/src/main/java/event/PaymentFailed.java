package event;

import common.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record PaymentFailed(
    UUID paymentId,
    UUID orderId,
    Instant occurredOn
) implements DomainEvent {

}