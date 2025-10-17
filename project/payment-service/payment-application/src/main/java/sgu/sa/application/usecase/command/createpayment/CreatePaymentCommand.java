package sgu.sa.application.usecase.command.createpayment;

import sgu.sa.core.type.Currency;
import sgu.sa.core.type.PaymentMethod;
import sgu.sa.application.mediator.Request;

import java.math.BigDecimal;
import java.util.UUID;

public record CreatePaymentCommand(
    UUID orderId,
    BigDecimal amount,
    Currency currency,
    PaymentMethod method
) implements Request<CreatePaymentResult> {
}