package sgu.sa.application.dto;

import sgu.sa.core.type.OrderStatus;

import java.time.Instant;

public record OrderStatusEntryDTO(
    OrderStatus status,
    Instant changedAt
) {
}
