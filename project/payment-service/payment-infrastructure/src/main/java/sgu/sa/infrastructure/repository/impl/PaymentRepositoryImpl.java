package sgu.sa.infrastructure.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sgu.sa.core.entity.Payment;
import sgu.sa.core.repository.PaymentRepository;
import sgu.sa.infrastructure.mapper.PaymentEntityMapper;
import sgu.sa.infrastructure.repository.jpa.PaymentJpaRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentEntityMapper paymentMapper;
    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public Payment save(Payment payment) {
        var paymentEntity = paymentMapper.toEntity(payment);
        var saved = paymentJpaRepository.save(paymentEntity);
        return paymentMapper.toDomain(saved);
    }

    @Override
    public Optional<Payment> findById(UUID paymentId) {
        return paymentJpaRepository
            .findById(paymentId)
            .map(paymentMapper::toDomain);
    }

    @Override
    public Optional<Payment> findByOrderId(UUID orderId) {
        return paymentJpaRepository
            .findByOrderId(orderId)
            .map(paymentMapper::toDomain);
    }
}
