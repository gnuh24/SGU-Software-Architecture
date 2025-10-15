package sgu.sa.application.event.ordercancel;

import sgu.sa.application.event.common.AppEvent;

import java.util.UUID;

public record OrderCanceledEvent(
    UUID orderId
) implements AppEvent {

}
