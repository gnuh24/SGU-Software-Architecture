package sgu.sa.application.event.paymentcompleted;

import sgu.sa.application.event.common.AppEvent;

import java.util.UUID;

public record PaymentCompletedEvent(
    UUID orderId
) implements AppEvent {
}
