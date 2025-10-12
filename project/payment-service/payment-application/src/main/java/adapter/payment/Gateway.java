package adapter.payment;

import type.Currency;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public interface Gateway {
    String createPaymentUrl(BigDecimal amount, Currency currency, UUID orderId);

    boolean verifyPaymentCallback(Map<String, String> callbackParams);
}
