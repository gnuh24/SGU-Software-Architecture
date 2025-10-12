package sgu.sa.payment.gateway;

import adapter.payment.Gateway;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sgu.sa.payment.config.VnPayProperties;
import type.Currency;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@RequiredArgsConstructor
@Getter
public class VnPayGateway implements Gateway {

    private final VnPayProperties props;

    @Override
    public String createPaymentUrl(BigDecimal amount, Currency currency, UUID orderId) {
        long amountVal = amount.multiply(new BigDecimal(100)).longValue();

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", getProps().getVersion());
        vnp_Params.put("vnp_Command", getProps().getCommand());
        vnp_Params.put("vnp_TmnCode", getProps().getTmnCode());
        vnp_Params.put("vnp_Amount", String.valueOf(amountVal));
        vnp_Params.put("vnp_CurrCode", currency.name());

        String vnp_TxnRef = orderId.toString();
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);

        vnp_Params.put("vnp_OrderInfo", "THANH TOAN DON HANG:" + vnp_TxnRef.toUpperCase());
        vnp_Params.put("vnp_OrderType", "other");
        vnp_Params.put("vnp_ReturnUrl", getProps().getReturnUrl());
        vnp_Params.put("vnp_IpAddr", getProps().getIpAddr());
        vnp_Params.put("vnp_Locale", getProps().getLocale() != null ? getProps().getLocale() : "vn");

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

        String vnp_SecureHash = hmacSHA512(getProps().getHashSecret(), hashData.toString());
        query.append("&vnp_SecureHash=").append(vnp_SecureHash);

        return getProps().getPayUrl() + "?" + query;
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

        String myHash = hmacSHA512(getProps().getHashSecret(), hashData.toString());
        return myHash.equalsIgnoreCase(secureHash);
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
