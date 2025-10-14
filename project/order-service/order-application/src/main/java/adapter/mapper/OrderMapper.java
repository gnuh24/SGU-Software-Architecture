package mapper;

import dto.OrderDTO;
import entity.Order;
import entity.OrderItem;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vo.ItemData;

import java.util.List;
import java.util.UUID;

@Mapper
public interface OrderMapper {
    OrderDTO toDto(Order order);

    @Mapping(target = "domainEvents", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Order toDomain(OrderDTO orderDTO);

}
