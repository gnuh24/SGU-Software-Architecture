package entity;

import common.DomainEvent;
import common.HasDomainEvent;
import event.*;
import exception.InvalidStatusException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import type.PaymentMethod;
import type.PaymentStatus;
import vo.Money;
import vo.PaymentStatusEntry;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements HasDomainEvent {
    private UUID paymentId;
    private UUID orderId;
    private Money amount;
    private PaymentMethod method;
    private PaymentStatus status;
    private List<PaymentStatusEntry> statusHistory = new ArrayList<>();
    private Instant createdAt;

    public Payment(UUID orderId, Money amount, PaymentMethod method) {
        this.paymentId = UUID.randomUUID();
        this.orderId = orderId;
        this.amount = amount;
        this.method = method;
        this.status = PaymentStatus.PENDING;
        this.createdAt = Instant.now();
        this.statusHistory.add(new PaymentStatusEntry(PaymentStatus.PENDING, this.createdAt));

        addDomainEvent(new PaymentCreated(paymentId, orderId, Instant.now()));
    }

    public void changeStatus(PaymentStatus newStatus) {
        if (!PaymentStatusEntry.isValidTransition(this.status, newStatus)) {
            var error = String.format("Không thể chuyển trạng thái từ %s sang %s", this.status, newStatus);
            throw new InvalidStatusException(error);
        }
        this.status = newStatus;
        Instant now = Instant.now();
        this.statusHistory.add(new PaymentStatusEntry(newStatus, now));

        switch (newStatus) {
            case COMPLETED -> addDomainEvent(new PaymentCompleted(paymentId, orderId, now));
            case FAILED -> addDomainEvent(new PaymentFailed(paymentId, orderId, now));
            case CANCELED -> addDomainEvent(new PaymentCanceled(paymentId, orderId, now));
            case EXPIRED -> addDomainEvent(new PaymentExpired(paymentId, orderId, now));
            case REFUNDED -> addDomainEvent(new PaymentRefunded(paymentId, orderId, now));
            default -> {
            }
        }
    }
}
