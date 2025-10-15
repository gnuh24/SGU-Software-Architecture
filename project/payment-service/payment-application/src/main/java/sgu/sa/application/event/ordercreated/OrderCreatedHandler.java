package sgu.sa.application.event.ordercreated;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgu.sa.application.event.common.AppEventHandler;
import sgu.sa.application.usecase.command.createpayment.CreatePaymentCommand;
import sgu.sa.application.usecase.command.createpayment.CreatePaymentHandler;
import sgu.sa.core.type.Currency;

@Service
@RequiredArgsConstructor
public class OrderCreatedHandler implements AppEventHandler<OrderCreatedEvent> {
    private final CreatePaymentHandler handler;

    @Override
    public void handle(OrderCreatedEvent event) {
        var command = new CreatePaymentCommand(
            event.orderId(),
            event.amount(),
            Currency.VND,
            event.method()
        );
        handler.handle(command);
    }
}
