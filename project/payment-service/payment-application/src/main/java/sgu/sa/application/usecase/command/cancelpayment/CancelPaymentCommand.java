package sgu.sa.application.usecase.command.cancelpayment;

import sgu.sa.application.mediator.Request;

import java.util.UUID;

public record CancelPaymentCommand(UUID orderId) implements Request<Void> {
}
