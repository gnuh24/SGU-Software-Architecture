package sgu.sa.application.usecase.command.createpaymenturl;

import lombok.RequiredArgsConstructor;
import sgu.sa.application.mediator.HandlerComponent;
import sgu.sa.application.mediator.RequestHandler;
import sgu.sa.application.port.payment.GatewayFactory;
import sgu.sa.core.type.Currency;

@HandlerComponent
@RequiredArgsConstructor
public class CreatePaymentUrlHandler implements RequestHandler<CreatePaymentUrlCommand, CreatePaymentUrlResult> {
    private final GatewayFactory factory;

    @Override
    public CreatePaymentUrlResult handle(CreatePaymentUrlCommand command) {
        var gateway = factory.getGateway(command.method());
        var paymentUrl = gateway.createPaymentUrl(command.amount(), Currency.VND, command.orderId());
        return new CreatePaymentUrlResult(paymentUrl);
    }
}
