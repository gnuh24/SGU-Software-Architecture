package mapper;

import entity.Order;
import org.mapstruct.Mapper;
import persistence.entity.OrderEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderEntityMapper {
    Order toDomain(OrderEntity order);
    OrderEntity toEntity(Order order);
    List<Order> toDomainList(List<OrderEntity> entities);
    List<OrderEntity> toEntityList(List<Order> domains);
}
