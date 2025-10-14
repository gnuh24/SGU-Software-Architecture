package sgu.sa.application.usecase.query.getorderpayment;

import sgu.sa.application.dto.PaymentDTO;

public record GetOrderPaymentResult(
    PaymentDTO payment
) {
}
