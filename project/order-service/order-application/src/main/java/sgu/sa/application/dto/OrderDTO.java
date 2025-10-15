package sgu.sa.application.dto;

import sgu.sa.core.type.OrderStatus;
import sgu.sa.core.type.PaymentMethod;
import sgu.sa.core.vo.Address;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OrderDTO(
    UUID orderId,
    UUID customerId,
    UUID restaurantId,
    BigDecimal totalPrice,
    PaymentMethod method,
    OrderStatus status,
    Address address,
    List<OrderItemDTO> items,
    List<OrderStatusEntryDTO> statusHistory
) {
}
