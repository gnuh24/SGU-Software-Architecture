package sgu.sa.application.usecase.query.getpayment;

import sgu.sa.application.mediator.Request;

import java.util.UUID;

public record GetPaymentQuery(
    UUID paymentId
) implements Request<GetPaymentResult> {
}
