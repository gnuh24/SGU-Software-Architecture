package sgu.sa.application.usecase.query.getorder;

import sgu.sa.application.usecase.common.Request;

import java.util.UUID;

public record GetOrderQuery(
    UUID orderId
) implements Request<GetOrderResult> {
}
