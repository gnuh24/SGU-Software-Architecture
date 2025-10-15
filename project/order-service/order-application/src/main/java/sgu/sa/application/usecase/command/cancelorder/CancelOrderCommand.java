package sgu.sa.application.usecase.command.cancelorder;

import sgu.sa.application.usecase.common.Request;

import java.util.UUID;

public record CancelOrderCommand(
    UUID orderId
) implements Request<Void> {
}
