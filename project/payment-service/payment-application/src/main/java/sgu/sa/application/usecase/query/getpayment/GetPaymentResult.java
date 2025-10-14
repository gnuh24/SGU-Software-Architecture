package sgu.sa.application.usecase.query.getpayment;

import sgu.sa.application.dto.PaymentDTO;

public record GetPaymentResult(
    PaymentDTO payment
) {
}
