package sgu.sa.application.usecase.command.refundpayment;

import sgu.sa.application.usecase.common.Request;

import java.util.UUID;

public record RefundPaymentCommand(
    UUID paymentId
) implements Request<Void> {
}
