package adapter.external;

import type.PaymentMethod;

public interface GatewayFactory {
    Gateway getGateway(PaymentMethod paymentMethod);
}
