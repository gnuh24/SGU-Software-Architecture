package sgu.sa.application.usecase.command.expirepayment;

import sgu.sa.application.usecase.common.Request;

import java.util.UUID;

public record ExpirePaymentCommand(UUID paymentId) implements Request<Void> {
}
