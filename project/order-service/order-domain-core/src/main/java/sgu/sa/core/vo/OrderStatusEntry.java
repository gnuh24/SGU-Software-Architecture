package sgu.sa.core.vo;

import sgu.sa.core.type.OrderStatus;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

public record OrderStatusEntry(OrderStatus status, Instant changedAt) {
    private static final Map<OrderStatus, Set<OrderStatus>> validTransitions = Map.of(
        OrderStatus.CREATED, Set.of(
            OrderStatus.PAID,
            OrderStatus.CANCELED
        )
    );

    public static boolean isValidTransition(OrderStatus from, OrderStatus to) {
        return validTransitions.getOrDefault(from, Set.of()).contains(to);
    }

}
