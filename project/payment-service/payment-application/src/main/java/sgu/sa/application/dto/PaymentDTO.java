package sgu.sa.application.dto;

import sgu.sa.core.vo.Money;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record PaymentDTO(
    UUID paymentId,
    UUID orderId,
    Money amount,
    String amountString,
    String method,
    String status,
    Instant createdAt,
    List<PaymentStatusEntryDTO> statusHistory
) {
}