package sgu.sa.messaging.mapper;

import event.order.OrderCreatedAvroEvent;
import sgu.sa.application.event.ordercreated.OrderCreatedEvent;

//@Mapper
public interface OrderEventMapper {
    OrderCreatedEvent toAppEvent(OrderCreatedAvroEvent avro);
}
