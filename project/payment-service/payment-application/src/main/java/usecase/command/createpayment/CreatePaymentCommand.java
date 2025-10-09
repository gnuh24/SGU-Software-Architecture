package usecase.command.createpayment;

import type.Currency;
import type.PaymentMethod;

import java.math.BigDecimal;
import java.util.UUID;

public record CreatePaymentCommand(
    UUID orderId,
    BigDecimal amount,
    Currency currency,
    PaymentMethod method
) {
}