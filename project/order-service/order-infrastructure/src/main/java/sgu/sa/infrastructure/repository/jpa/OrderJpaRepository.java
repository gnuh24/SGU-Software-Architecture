package sgu.sa.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import sgu.sa.infrastructure.persistence.entity.OrderEntity;

import java.util.List;
import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
    List<OrderEntity> findByCustomerId(UUID customerId);
}
