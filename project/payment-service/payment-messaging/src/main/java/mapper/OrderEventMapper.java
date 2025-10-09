package mapper;

import event.order.OrderCreatedAvroEvent;
import org.mapstruct.Mapper;
import usecase.event.ordercreated.OrderCreatedEvent;

@Mapper(componentModel = "spring")
public interface OrderEventMapper {
    OrderCreatedEvent toAppEvent(OrderCreatedAvroEvent avro);
}
