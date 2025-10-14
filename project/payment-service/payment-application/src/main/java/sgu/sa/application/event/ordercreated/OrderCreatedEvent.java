package sgu.sa.application.event.ordercreated;

import sgu.sa.core.type.Currency;
import sgu.sa.core.type.PaymentMethod;
import sgu.sa.application.event.common.AppEvent;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderCreatedEvent(
    UUID orderId,
    BigDecimal amount,
    Currency currency,
    PaymentMethod method
) implements AppEvent {

}
