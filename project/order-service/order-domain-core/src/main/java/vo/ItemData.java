package vo;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemData(UUID productId, int quantity, BigDecimal price) {}
