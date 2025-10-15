package sgu.sa.application.event.paymentcompleted;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgu.sa.application.usecase.command.payorder.PayOrderCommand;
import sgu.sa.application.usecase.command.payorder.PayOrderHandler;
import sgu.sa.application.event.common.AppEventHandler;

@Service
@RequiredArgsConstructor
public class PaymentCompletedHandler implements AppEventHandler<PaymentCompletedEvent> {
    private final PayOrderHandler handler;

    @Override
    public void handle(PaymentCompletedEvent event) {
        var command = new PayOrderCommand(event.orderId());
        handler.handle(command);
    }
}
