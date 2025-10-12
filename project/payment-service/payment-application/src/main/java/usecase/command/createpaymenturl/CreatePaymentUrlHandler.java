package usecase.command.createpaymenturl;

import adapter.payment.GatewayFactory;
import type.Currency;

public record CreatePaymentUrlHandler(GatewayFactory factory) {
    public CreatePaymentUrlResult handle(CreatePaymentUrlCommand command) {
        var gateway = factory().getGateway(command.method());
        var paymentUrl = gateway.createPaymentUrl(command.amount(), Currency.VND, command.orderId());
        return new CreatePaymentUrlResult(paymentUrl);
    }
}
