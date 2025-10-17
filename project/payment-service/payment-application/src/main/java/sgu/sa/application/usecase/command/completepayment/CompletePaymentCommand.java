package sgu.sa.application.usecase.command.completepayment;

import sgu.sa.application.mediator.Request;

import java.util.UUID;

public record CompletePaymentCommand(UUID paymentId) implements Request<Void> {
}
