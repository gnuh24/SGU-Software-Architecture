package sgu.sa.application.usecase.command.createpaymenturl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgu.sa.application.port.payment.GatewayFactory;
import sgu.sa.application.usecase.common.RequestHandler;
import sgu.sa.core.type.Currency;

@Service
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
