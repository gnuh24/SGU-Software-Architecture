package sgu.sa.application.dto;

import sgu.sa.core.type.PaymentStatus;

import java.time.Instant;

public record PaymentStatusEntryDTO(
    PaymentStatus status,
    Instant changedAt
) {
}
