package sgu.sa.payment.gateway;

import adapter.payment.Gateway;
import org.springframework.stereotype.Component;
import type.Currency;

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
