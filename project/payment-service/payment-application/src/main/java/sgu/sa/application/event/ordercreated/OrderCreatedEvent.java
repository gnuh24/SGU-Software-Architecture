package sgu.sa.application.event.ordercreated;

import sgu.sa.application.event.common.AppEvent;
import sgu.sa.core.type.PaymentMethod;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderCreatedEvent(
    UUID orderId,
    BigDecimal amount,
    PaymentMethod method
) implements AppEvent {

}
