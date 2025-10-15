package sgu.sa.infrastructure.mapper;

import sgu.sa.core.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sgu.sa.infrastructure.persistence.entity.OrderEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderEntityMapper {
    @Mapping(target = "domainEvents", ignore = true)
    Order toDomain(OrderEntity order);

    @Mapping(target = "domainEvents", ignore = true)
    OrderEntity toEntity(Order order);
    List<Order> toDomainList(List<OrderEntity> entities);
    List<OrderEntity> toEntityList(List<Order> domains);
}
