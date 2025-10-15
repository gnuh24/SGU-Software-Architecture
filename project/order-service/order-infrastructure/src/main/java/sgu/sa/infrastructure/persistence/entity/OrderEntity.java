package sgu.sa.infrastructure.persistence.entity;

import sgu.sa.core.common.HasDomainEvent; // Giả sử bạn có interface này
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgu.sa.infrastructure.persistence.embedded.AddressEmbedded;
import sgu.sa.infrastructure.persistence.embedded.OrderStatusEntryEmbedded;
import sgu.sa.core.type.OrderStatus;
import sgu.sa.core.type.PaymentMethod;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity implements HasDomainEvent {

    @Id
    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "restaurant_id", nullable = false)
    private UUID restaurantId;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus status;

    @Embedded
    private AddressEmbedded address;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderItemEntity> items;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "order_status_history",
        joinColumns = @JoinColumn(name = "order_id")
    )
    @OrderBy("changedAt ASC")
    private List<OrderStatusEntryEmbedded> statusHistory = new ArrayList<>();
}