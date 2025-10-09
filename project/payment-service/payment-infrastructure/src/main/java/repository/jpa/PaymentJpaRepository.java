package repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import persistence.entity.PaymentEntity;

import java.util.Optional;
import java.util.UUID;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, UUID> {
    Optional<PaymentEntity> findByOrderId(UUID orderId);
}
