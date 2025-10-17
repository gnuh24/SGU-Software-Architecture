package sgu.sa.application.usecase.command.refundpayment;

import sgu.sa.application.mediator.Request;

import java.util.UUID;

public record RefundPaymentCommand(
    UUID paymentId
) implements Request<Void> {
}
