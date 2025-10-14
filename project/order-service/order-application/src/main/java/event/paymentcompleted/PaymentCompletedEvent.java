package event.paymentcompleted;

import event.common.AppEvent;

import java.util.UUID;

public record PaymentCompletedEvent(
    UUID orderId
) implements AppEvent {
}
