package sgu.sa.application.mapper;

import sgu.sa.application.dto.OrderDTO;
import sgu.sa.core.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderDtoMapper {
    OrderDTO toDto(Order order);

    @Mapping(target = "domainEvents", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Order toDomain(OrderDTO orderDTO);

    List<OrderDTO> toDtoList(List<Order> orders);
    List<Order> toDomainList(List<OrderDTO> orderDTOs);

}
