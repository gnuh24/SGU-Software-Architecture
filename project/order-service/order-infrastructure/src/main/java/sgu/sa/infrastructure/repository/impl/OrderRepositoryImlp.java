package sgu.sa.infrastructure.repository.impl;

import sgu.sa.core.entity.Order;
import lombok.RequiredArgsConstructor;
import sgu.sa.infrastructure.mapper.OrderEntityMapper;
import org.springframework.stereotype.Repository;
import sgu.sa.core.repository.OrderRepository;
import sgu.sa.infrastructure.repository.jpa.OrderJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImlp implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;
    private final OrderEntityMapper orderMapper;
    @Override
    public Order save(Order order) {
        var orderEntity = orderMapper.toEntity(order);
        var saved = orderJpaRepository.save(orderEntity);
        return orderMapper.toDomain(saved);
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return orderJpaRepository.findById(id)
            .map(orderMapper::toDomain);
    }

    @Override
    public List<Order> findByCustomerId(UUID id) {
        var orders = orderJpaRepository.findByCustomerId(id);
        return orderMapper.toDomainList(orders);
    }
}
