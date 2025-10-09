package usecase.command.createpayment;

import dto.PaymentDTO;

public record CreatePaymentResult(
    PaymentDTO payment
) {
}
