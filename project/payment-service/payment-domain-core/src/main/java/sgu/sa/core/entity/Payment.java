package sgu.sa.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgu.sa.core.common.HasDomainEvent;
import sgu.sa.core.event.*;
import sgu.sa.core.exception.InvalidStatusException;
import sgu.sa.core.type.PaymentMethod;
import sgu.sa.core.type.PaymentStatus;
import sgu.sa.core.vo.Money;
import sgu.sa.core.vo.PaymentStatusEntry;

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

    public static Payment create(UUID orderId, Money amount, PaymentMethod method) {
        Payment payment = new Payment();
        payment.setPaymentId(UUID.randomUUID());
        payment.setOrderId(orderId);
        payment.setAmount(amount);
        payment.setMethod(method);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setCreatedAt(Instant.now());
        payment.getStatusHistory().add(new PaymentStatusEntry(PaymentStatus.PENDING, Instant.now()));
        payment.addDomainEvent(new PaymentCreated(payment.getPaymentId(), orderId, Instant.now()));
        return payment;
    }

    public void changeStatus(PaymentStatus newStatus) {
        if (!PaymentStatusEntry.isValidTransition(getStatus(), newStatus)) {
            var error = String.format("Không thể chuyển trạng thái từ %s sang %s", getStatus(), newStatus);
            throw new InvalidStatusException(error);
        }
        this.setStatus(newStatus);
        Instant now = Instant.now();
        this.getStatusHistory().add(new PaymentStatusEntry(newStatus, now));

        switch (newStatus) {
            case COMPLETED -> addDomainEvent(new PaymentCompleted(getPaymentId(), getOrderId(), now));
            case FAILED -> addDomainEvent(new PaymentFailed(getPaymentId(), getOrderId(), now));
            case CANCELED -> addDomainEvent(new PaymentCanceled(getPaymentId(), getOrderId(), now));
            case EXPIRED -> addDomainEvent(new PaymentExpired(getPaymentId(), getOrderId(), now));
            case REFUNDED -> addDomainEvent(new PaymentRefunded(getPaymentId(), getOrderId(), now));
            default -> {
            }
        }
    }
}
