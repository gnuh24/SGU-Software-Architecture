package sgu.sa.container.payment.gateway;

import org.springframework.stereotype.Component;
import sgu.sa.application.port.payment.Gateway;
import sgu.sa.core.type.Currency;
import sgu.sa.core.type.PaymentStatus;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;

@Component
public class ZaloPayGateway implements Gateway {

    @Override
    public String createPaymentUrl(BigDecimal amount, Currency currency, UUID orderId) {
        return "";
    }

    @Override
    public boolean verifyPaymentCallback(Map<String, String> callbackParams) {
        return false;
    }

    @Override
    public void handleCallback(Map<String, String> callbackParams, BiConsumer<UUID, PaymentStatus> onComplete) {

    }

}
