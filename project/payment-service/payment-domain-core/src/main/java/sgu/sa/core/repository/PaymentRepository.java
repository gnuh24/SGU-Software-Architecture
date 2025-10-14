package sgu.sa.core.repository;

import sgu.sa.core.entity.Payment;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {
    Payment save(Payment payment);
    Optional<Payment> findById(UUID paymentId);
    Optional<Payment> findByOrderId(UUID orderId);
}