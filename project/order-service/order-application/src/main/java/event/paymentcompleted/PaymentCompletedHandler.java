package event.paymentcompleted;

import usecase.command.payorder.PayOrderCommand;
import usecase.command.payorder.PayOrderHandler;
import event.common.AppEventHandler;

public record PaymentCompletedHandler(
    PayOrderHandler handler
)implements AppEventHandler<PaymentCompletedEvent> {

    @Override
    public void handle(PaymentCompletedEvent event) {
        var command = new PayOrderCommand(event.orderId());
        handler.handle(command);
    }
}
