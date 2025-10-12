package adapter.payment;

import type.PaymentMethod;

public interface GatewayFactory {
    Gateway getGateway(PaymentMethod paymentMethod);
}
