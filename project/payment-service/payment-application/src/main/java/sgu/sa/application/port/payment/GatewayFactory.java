package sgu.sa.application.port.payment;

import sgu.sa.core.type.PaymentMethod;

public interface GatewayFactory {
    Gateway getGateway(PaymentMethod paymentMethod);
}
