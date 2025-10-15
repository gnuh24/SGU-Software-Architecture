package sgu.sa.core.event;

import sgu.sa.core.common.DomainEvent;
import sgu.sa.core.vo.ItemData;
import sgu.sa.core.type.PaymentMethod;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OrderCreated(
    UUID orderId,
    UUID customerId,
    BigDecimal totalPrice,
    PaymentMethod method,
    List<ItemData> items,
    Instant occurredOn
) implements DomainEvent {
}

