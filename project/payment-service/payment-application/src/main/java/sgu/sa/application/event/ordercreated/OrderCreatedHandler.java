package sgu.sa.application.event.ordercreated;

import sgu.sa.application.event.common.AppEventHandler;
import sgu.sa.application.usecase.command.createpayment.CreatePaymentCommand;
import sgu.sa.application.usecase.command.createpayment.CreatePaymentHandler;

public record OrderCreatedHandler(
    CreatePaymentHandler createPaymentHandler
) implements AppEventHandler<OrderCreatedEvent> {
    @Override
    public void handle(OrderCreatedEvent event) {
        var command = new CreatePaymentCommand(
            event.orderId(),
            event.amount(),
            event.currency(),
            event.method()
        );
        createPaymentHandler.handle(command);
    }
}
