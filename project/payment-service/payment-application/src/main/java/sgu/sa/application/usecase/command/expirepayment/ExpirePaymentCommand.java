package sgu.sa.application.usecase.command.expirepayment;

import sgu.sa.application.mediator.Request;

import java.util.UUID;

public record ExpirePaymentCommand(UUID paymentId) implements Request<Void> {
}
