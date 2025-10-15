package sgu.sa.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemDTO(
    UUID productId,
    int quantity,
    BigDecimal price
) {
}
