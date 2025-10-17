package sgu.sa.application.usecase.command.failpayment;

import sgu.sa.application.mediator.Request;

import java.util.UUID;

public record FailPaymentCommand(UUID paymentId) implements Request<Void> {
}
