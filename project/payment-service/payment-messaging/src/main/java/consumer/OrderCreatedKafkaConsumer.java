package consumer;

import event.order.OrderCreatedAvroEvent;
import lombok.AllArgsConstructor;
import mapper.OrderEventMapper;
import usecase.event.ordercreated.OrderCreatedHandler;

@AllArgsConstructor
public class OrderCreatedKafkaConsumer {
    private final OrderCreatedHandler handler;
    private final OrderEventMapper mapper;

    public void consume(OrderCreatedAvroEvent avroEvent) {
        handler.handle(mapper.toAppEvent(avroEvent));
    }
}
