package mapper;

import entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vo.ItemData;

import java.util.List;
import java.util.UUID;

@Mapper
public interface OrderItemMapper {
    @Mapping(target = "id", defaultValue = "rndomuuid")
    @Mapping(source = "orderId", target = "orderId")
    OrderItem toDomain(ItemData item, UUID orderId);

    List<OrderItem> toDomainList(List<ItemData> items, UUID orderId);

}
