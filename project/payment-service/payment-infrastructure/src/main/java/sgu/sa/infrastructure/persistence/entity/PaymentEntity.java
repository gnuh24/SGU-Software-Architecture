package sgu.sa.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgu.sa.core.common.HasDomainEvent;
import sgu.sa.core.type.PaymentMethod;
import sgu.sa.core.type.PaymentStatus;
import sgu.sa.infrastructure.persistence.embedded.MoneyEmbedded;
import sgu.sa.infrastructure.persistence.embedded.PaymentStatusEntryEmbedded;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class PaymentEntity implements HasDomainEvent {

    @Id
    @Column(name = "payment_id")
    private UUID paymentId;

    @Column(name = "order_id", nullable = false, unique = true)
    private UUID orderId;

    @Embedded
    private MoneyEmbedded amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "payment_status_history",
        joinColumns = @JoinColumn(name = "payment_id")
    )
    @OrderBy("changedAt ASC")
    private List<PaymentStatusEntryEmbedded> statusHistory = new ArrayList<>();
}