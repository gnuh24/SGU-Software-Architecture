package usecase.command.createpaymenturl;

import type.PaymentMethod;

import java.math.BigDecimal;
import java.util.UUID;

public record CreatePaymentUrlCommand(
    BigDecimal amount,
    UUID orderId,
    PaymentMethod method
) {
}
