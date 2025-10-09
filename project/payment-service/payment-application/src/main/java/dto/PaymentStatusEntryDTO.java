package dto;

import java.time.Instant;

public record PaymentStatusEntryDTO(
    String status,
    Instant changedAt
) {
}
