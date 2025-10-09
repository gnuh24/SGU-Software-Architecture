package usecase.query.getorderpayment;

import dto.PaymentDTO;

public record GetOrderPaymentResult(
    PaymentDTO payment
) {
}
