package sgu.sa.application.port.payment;

import sgu.sa.core.type.Currency;
import sgu.sa.core.type.PaymentStatus;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;

public interface Gateway {
    String createPaymentUrl(BigDecimal amount, Currency currency, UUID orderId);

    boolean verifyPaymentCallback(Map<String, String> callbackParams);

    void handleCallback(
        Map<String, String> callbackParams,
        BiConsumer<UUID, PaymentStatus> onComplete
    );
}
