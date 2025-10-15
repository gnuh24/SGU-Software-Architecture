package sgu.sa.application.usecase.command.payorder;

import sgu.sa.application.usecase.common.Request;

import java.util.UUID;

public record PayOrderCommand(
    UUID orderId
) implements Request<Void> {

}
