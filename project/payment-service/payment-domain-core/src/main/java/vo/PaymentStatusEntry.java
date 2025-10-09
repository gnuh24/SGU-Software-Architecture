package vo;

import type.PaymentStatus;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

public record PaymentStatusEntry(PaymentStatus status, Instant changedAt) {
    private static final Map<PaymentStatus, Set<PaymentStatus>> validTransitions = Map.of(
        PaymentStatus.PENDING, Set.of(
            PaymentStatus.COMPLETED,
            PaymentStatus.FAILED,
            PaymentStatus.CANCELED,
            PaymentStatus.EXPIRED
        ),
        PaymentStatus.COMPLETED, Set.of(
            PaymentStatus.REFUNDED
        )
    );

    public static boolean isValidTransition(PaymentStatus from, PaymentStatus to) {
        return validTransitions.getOrDefault(from, Set.of()).contains(to);
    }

}