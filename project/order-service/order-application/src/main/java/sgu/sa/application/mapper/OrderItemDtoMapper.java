package sgu.sa.application.mapper;

import org.mapstruct.BeanMapping;
import sgu.sa.core.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sgu.sa.core.vo.ItemData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderItemDtoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderId", ignore = true)
    OrderItem toDomain(ItemData item);

    default ArrayList<OrderItem> toDomainList(List<ItemData> items) {
        return items
            .stream()
            .map(this::toDomain)
            .collect(Collectors.toCollection(ArrayList::new));
    }
}
