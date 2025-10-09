package adapter.external;

import entity.Payment;
import type.PaymentMethod;
import type.PaymentStatus;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

public interface Gateway {
    String createPaymentUrl(BigDecimal amount, Currency currency);

    boolean verifyPaymentCallback(Map<String, String> callbackParams);
}
