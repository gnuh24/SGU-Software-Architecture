package repository.impl;

import entity.Payment;
import lombok.RequiredArgsConstructor;
import mapper.PaymentMapper;
import org.springframework.stereotype.Repository;
import repository.PaymentRepository;
import repository.jpa.PaymentJpaRepository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentMapper paymentMapper;

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
