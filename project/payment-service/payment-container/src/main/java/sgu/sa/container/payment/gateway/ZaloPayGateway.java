package sgu.sa.container.payment.gateway;

import sgu.sa.application.port.payment.Gateway;
import org.springframework.stereotype.Component;
import sgu.sa.core.type.Currency;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

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
}
