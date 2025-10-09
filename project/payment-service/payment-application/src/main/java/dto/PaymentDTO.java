package dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record PaymentDTO(
    UUID paymentId,
    UUID orderId,
    BigDecimal amount,
    String currency,
    String amountString,
    String method,
    String status,
    Instant createdAt,
    List<PaymentStatusEntryDTO> statusHistory
) {
}