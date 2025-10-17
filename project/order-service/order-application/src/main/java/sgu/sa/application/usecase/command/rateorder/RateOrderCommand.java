package sgu.sa.application.usecase.command.rateorder;

import sgu.sa.application.usecase.common.Request;

import java.util.UUID;

public record RateOrderCommand(
        UUID orderId,
        Integer rating) implements Request<Void> {

}
