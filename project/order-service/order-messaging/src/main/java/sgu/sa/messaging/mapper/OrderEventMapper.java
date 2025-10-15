package sgu.sa.messaging.mapper;

import event.avro.order.OrderCanceledAvroEvent;
import event.avro.order.OrderCreatedAvroEvent;
import event.avro.order.OrderPaidAvroEvent;
import org.mapstruct.Mapper;
import sgu.sa.core.event.OrderCanceledEvent;
import sgu.sa.core.event.OrderCreatedEvent;
import sgu.sa.core.event.OrderPaidEvent;

@Mapper(componentModel = "spring")
public interface OrderEventMapper {
    OrderCreatedAvroEvent toAvro(OrderCreatedEvent event);
    OrderCanceledAvroEvent toAvro(OrderCanceledEvent event);
    OrderPaidAvroEvent toAvro(OrderPaidEvent event);
}
