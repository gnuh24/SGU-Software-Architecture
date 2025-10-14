package sgu.sa.application.port.payment;

import sgu.sa.core.type.Currency;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public interface Gateway {
    String createPaymentUrl(BigDecimal amount, Currency currency, UUID orderId);

    boolean verifyPaymentCallback(Map<String, String> callbackParams);
}
