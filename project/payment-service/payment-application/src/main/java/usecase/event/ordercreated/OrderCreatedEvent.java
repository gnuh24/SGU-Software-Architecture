package usecase.event.ordercreated;

import type.Currency;
import type.PaymentMethod;
import usecase.event.common.AppEvent;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderCreatedEvent(
    UUID orderId,
    BigDecimal amount,
    Currency currency,
    PaymentMethod method
) implements AppEvent {

}
