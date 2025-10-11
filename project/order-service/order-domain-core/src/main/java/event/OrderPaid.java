package event;

import common.DomainEvent;
import event.data.ItemData;
import type.PaymentMethod;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OrderPaid(
    UUID orderId,
    Instant occurredOn
) implements DomainEvent {
}
