package sgu.sa.application.usecase.command.cancelpayment;

import sgu.sa.application.usecase.common.Request;

import java.util.UUID;

public record CancelPaymentCommand(UUID orderId) implements Request<Void> {
}
