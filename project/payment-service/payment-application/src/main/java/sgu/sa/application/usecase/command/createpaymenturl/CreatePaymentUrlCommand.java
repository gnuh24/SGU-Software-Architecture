package sgu.sa.application.usecase.command.createpaymenturl;

import sgu.sa.core.type.PaymentMethod;
import sgu.sa.application.usecase.common.Request;

import java.math.BigDecimal;
import java.util.UUID;

public record CreatePaymentUrlCommand(
    BigDecimal amount,
    UUID orderId,
    PaymentMethod method
) implements Request<CreatePaymentUrlResult> {
}
