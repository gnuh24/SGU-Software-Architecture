package sgu.sa.core.entity;

import sgu.sa.core.common.HasDomainEvent;
import sgu.sa.core.event.OrderCanceledEvent;
import sgu.sa.core.event.OrderCreatedEvent;
import sgu.sa.core.event.OrderPaidEvent;
import sgu.sa.core.vo.ItemData;
import sgu.sa.core.exception.InvalidStatusException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgu.sa.core.type.OrderStatus;
import sgu.sa.core.type.PaymentMethod;
import sgu.sa.core.vo.Address;
import sgu.sa.core.vo.OrderStatusEntry;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order implements HasDomainEvent {
    private UUID orderId;
    private UUID customerId;
    private UUID restaurantId;
    private PaymentMethod method;
    private OrderStatus status;
    private Address address;
    private Instant createdAt;

    private List<OrderItem> items = new ArrayList<>();
    private List<OrderStatusEntry> statusHistory = new ArrayList<>();

    public static Order create(
        UUID customerId,
        UUID restaurantId,
        PaymentMethod method,
        Address address,
        List<ItemData> items
    )  {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID());
        order.setCustomerId(customerId);
        order.setRestaurantId(restaurantId);
        order.setMethod(method);
        order.setStatus(OrderStatus.CREATED);
        order.getStatusHistory().add(new OrderStatusEntry(OrderStatus.CREATED, Instant.now()));
        order.setAddress(address);
        order.setCreatedAt(Instant.now());
        order.addItems(items);

        order.addDomainEvent(new OrderCreatedEvent(
            order.getOrderId(),
            order.getCustomerId(),
            order.getTotalPrice(),
            order.getMethod(),
            items,
            Instant.now()
        ));
        return order;
    }

    public BigDecimal getTotalPrice() {
        return getItems().stream()
            .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addItems(List<ItemData> items) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        List<OrderItem> orderItems = items.stream()
            .map(item -> OrderItem.create(
                this.getOrderId(),
                item.productId(),
                item.quantity(),
                item.price())
            )
            .toList();
        this.items.addAll(orderItems);
    }

    public void changeStatus(OrderStatus newStatus) {
        if (!OrderStatusEntry.isValidTransition(getStatus(), newStatus)) {
            var error = String.format("Không thể chuyển trạng thái từ %s sang %s", getStatus(), newStatus);
            throw new InvalidStatusException(error);
        }
        this.setStatus(newStatus);
        Instant now = Instant.now();
        this.getStatusHistory().add(new OrderStatusEntry(newStatus, now));

        switch (newStatus) {
            case PAID -> addDomainEvent(new OrderPaidEvent(getOrderId(), now));
            case CANCELED -> addDomainEvent(new OrderCanceledEvent(getOrderId(), now));
            default -> {
            }
        }
    }
}
