package sgu.sa.application.usecase.command.verifypayment;

import sgu.sa.application.mediator.Request;
import sgu.sa.core.type.PaymentMethod;

import java.util.Map;

public record VerifyPaymentCommand(
    Map<String, String> params,
    PaymentMethod method
) implements Request<Void> {
}
