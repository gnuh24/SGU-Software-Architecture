package sgu.sa.application.usecase.command.createpayment;

import sgu.sa.application.dto.PaymentDTO;

public record CreatePaymentResult(
    PaymentDTO payment
) {
}
