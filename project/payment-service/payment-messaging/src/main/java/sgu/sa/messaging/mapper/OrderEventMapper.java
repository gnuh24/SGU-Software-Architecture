package sgu.sa.messaging.mapper;

import event.avro.order.OrderCanceledAvroEvent;
import event.avro.order.OrderCreatedAvroEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sgu.sa.application.event.ordercancel.OrderCanceledEvent;
import sgu.sa.application.event.ordercreated.OrderCreatedEvent;

@Mapper(componentModel = "spring")
public interface OrderEventMapper {
    @Mapping(target = "amount", source = "avroEvent.totalPrice")
    OrderCreatedEvent toAppEvent(OrderCreatedAvroEvent avroEvent);

    OrderCanceledEvent toAppEvent(OrderCanceledAvroEvent avroEvent);
}
