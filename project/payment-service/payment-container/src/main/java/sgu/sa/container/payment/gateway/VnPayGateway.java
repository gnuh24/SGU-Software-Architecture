package sgu.sa.container.payment.gateway;

import org.springframework.stereotype.Component;
import sgu.sa.application.port.payment.Gateway;
import sgu.sa.container.payment.config.VnPayProperties;
import sgu.sa.core.type.Currency;
import sgu.sa.core.type.PaymentStatus;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.BiConsumer;

@Component
public record VnPayGateway(
    VnPayProperties props
) implements Gateway {

    @Override
    public String createPaymentUrl(BigDecimal amount, Currency currency, UUID orderId) {
        long amountVal = amount.multiply(new BigDecimal(100)).longValue();

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", props().getVersion());
        vnp_Params.put("vnp_Command", props().getCommand());
        vnp_Params.put("vnp_TmnCode", props().getTmnCode());
        vnp_Params.put("vnp_Amount", String.valueOf(amountVal));
        vnp_Params.put("vnp_CurrCode", currency.toString());

        String vnp_TxnRef = orderId.toString();
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);

        vnp_Params.put("vnp_OrderInfo", "THANH TOAN DON HANG:" + vnp_TxnRef.toUpperCase());
        vnp_Params.put("vnp_OrderType", "other");
        vnp_Params.put("vnp_ReturnUrl", props().getReturnUrl());
        vnp_Params.put("vnp_IpAddr", props().getIpAddr());
        vnp_Params.put("vnp_Locale", props().getLocale() != null ? props().getLocale() : "vn");

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        String vnp_CreateDate = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        String vnp_ExpireDate = now.plusMinutes(15).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);

        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();

        for (Iterator<String> it = fieldNames.iterator(); it.hasNext();) {
            String fieldName = it.next();
            String fieldValue = vnp_Params.get(fieldName);
            if (fieldValue != null && !fieldValue.isEmpty()) {
                hashData.append(fieldName)
                    .append("=")
                    .append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII))
                    .append("=")
                    .append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                if (it.hasNext()) {
                    hashData.append("&");
                    query.append("&");
                }
            }
        }

        String vnp_SecureHash = hmacSHA512(props().getHashSecret(), hashData.toString());
        query.append("&vnp_SecureHash=").append(vnp_SecureHash);

        return props().getPayUrl() + "?" + query;
    }

    @Override
    public boolean verifyPaymentCallback(Map<String, String> callbackParams) {
        String secureHash = callbackParams.get("vnp_SecureHash");
        Map<String, String> sorted = new TreeMap<>();
        for (Map.Entry<String, String> e : callbackParams.entrySet()) {
            String key = e.getKey();
            if (e.getValue() == null || e.getValue().isEmpty()) continue;
            if (key.equals("vnp_SecureHash") || key.equals("vnp_SecureHashType")) continue;
            sorted.put(key, e.getValue());
        }

        StringBuilder hashData = new StringBuilder();
        Iterator<Map.Entry<String, String>> it = sorted.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> e = it.next();
            hashData.append(e.getKey())
                .append("=")
                .append(URLEncoder.encode(e.getValue(), StandardCharsets.US_ASCII));
            if (it.hasNext()) {
                hashData.append("&");
            }
        }

        String myHash = hmacSHA512(props().getHashSecret(), hashData.toString());
        return myHash.equalsIgnoreCase(secureHash);
    }

    @Override
    public void handleCallback(
        Map<String, String> callbackParams,
        BiConsumer<UUID, PaymentStatus> onComplete
    ) {
        if (!verifyPaymentCallback(callbackParams)) {
            throw new RuntimeException("Payment callback verification failed");
        }
        String orderIdStr = callbackParams.get("vnp_TxnRef");
        String responseCode = callbackParams.get("vnp_ResponseCode");

        if (orderIdStr == null || responseCode == null) {
            throw new IllegalArgumentException("Missing required callback parameters (vnp_TxnRef or vnp_ResponseCode)");
        }

        try {
            UUID orderId = UUID.fromString(orderIdStr);
            PaymentStatus status = switch (responseCode) {
                case "00" -> PaymentStatus.COMPLETED;
                case "24" -> PaymentStatus.CANCELED;
                case "11" -> PaymentStatus.EXPIRED;
                default -> PaymentStatus.FAILED;
            };
            onComplete.accept(orderId, status);

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid Order ID format in callback", e);
        }

    }

    private String hmacSHA512(String key, String data) {
        try {
            Mac hmac = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            hmac.init(secretKey);
            byte[] bytes = hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(2 * bytes.length);
            for (byte b : bytes) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error when generating HMAC SHA512", e);
        }
    }
}
