package sgu.sa.application.event.ordercancel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgu.sa.application.event.common.AppEventHandler;
import sgu.sa.application.usecase.command.cancelpayment.CancelPaymentCommand;
import sgu.sa.application.usecase.command.cancelpayment.CancelPaymentHandler;

@Service
@RequiredArgsConstructor
public class OrderCanceledHandler implements AppEventHandler<OrderCanceledEvent> {
    private final CancelPaymentHandler handler;

    @Override
    public void handle(OrderCanceledEvent event) {
        var command = new CancelPaymentCommand(event.orderId());
        handler.handle(command);
    }
}
