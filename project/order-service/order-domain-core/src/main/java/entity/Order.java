package entity;

import common.DomainEvent;
import common.HasDomainEvent;
import event.OrderCancel;
import event.OrderCreated;
import event.OrderPaid;
import event.data.ItemData;
import exception.InvalidStatusException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import type.OrderStatus;
import type.PaymentMethod;
import vo.Address;
import vo.OrderStatusEntry;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order implements HasDomainEvent {
    private UUID orderId;
    private UUID customerId;
    private UUID restaurantId;
    private BigDecimal totalPrice;
    private PaymentMethod method;
    private OrderStatus status;
    private Address address;
    private Instant createdAt;

    private List<OrderItem> items = new ArrayList<>();
    private List<OrderStatusEntry> statusHistory = new ArrayList<>();

    public static Order create(
        UUID customerId,
        UUID restaurantId,
        BigDecimal totalPrice,
        PaymentMethod method,
        OrderItem items,
        Address address
    )  {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID());
        order.setCustomerId(customerId);
        order.setRestaurantId(restaurantId);
        order.setTotalPrice(totalPrice);
        order.setMethod(method);
        order.setStatus(OrderStatus.CREATED);
        order.getStatusHistory().add(new OrderStatusEntry(OrderStatus.CREATED, Instant.now()));
        order.getItems().add(items);
        order.setAddress(address);
        order.setCreatedAt(Instant.now());

        var itemData = order.getItems().stream()
            .map(item -> new ItemData(item.getProductId(), item.getQuantity(), item.getPrice()))
            .toList();

        order.addDomainEvent(new OrderCreated(
            order.getOrderId(),
            order.getCustomerId(),
            order.getTotalPrice(),
            order.getMethod(),
            itemData,
            Instant.now()
        ));
        return order;
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
            case PAID -> addDomainEvent(new OrderPaid(getOrderId(), now));
            case CANCELED -> addDomainEvent(new OrderCancel(getOrderId(), now));
            default -> {
            }
        }
    }
}
