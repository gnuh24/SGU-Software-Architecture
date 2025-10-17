package sgu.sa.application.usecase.query.getorderpayment;

import sgu.sa.application.mediator.Request;

import java.util.UUID;

public record GetOrderPaymentQuery(
    UUID orderId
) implements Request<GetOrderPaymentResult> {

}
