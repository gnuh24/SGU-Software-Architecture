package usecase.event.ordercreated;

import lombok.RequiredArgsConstructor;
import usecase.event.common.AppEventHandler;
import lombok.AllArgsConstructor;
import usecase.command.createpayment.CreatePaymentCommand;
import usecase.command.createpayment.CreatePaymentHandler;

@RequiredArgsConstructor
public class OrderCreatedHandler implements AppEventHandler<OrderCreatedEvent> {

    private CreatePaymentHandler createPaymentHandler;

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
