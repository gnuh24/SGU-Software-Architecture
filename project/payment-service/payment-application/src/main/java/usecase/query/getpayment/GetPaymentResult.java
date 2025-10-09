package usecase.query.getpayment;

import dto.PaymentDTO;

public record GetPaymentResult(
    PaymentDTO payment
) {
}
