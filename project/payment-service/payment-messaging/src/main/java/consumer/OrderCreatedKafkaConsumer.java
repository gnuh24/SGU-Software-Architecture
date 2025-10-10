package consumer;

import event.order.OrderCreatedAvroEvent;
import lombok.AllArgsConstructor;
import usecase.event.ordercreated.OrderCreatedHandler;

@AllArgsConstructor
public class OrderCreatedKafkaConsumer {
    private final OrderCreatedHandler handler;
//    private final OrderEventMapper mapper;

//    @KafkaListener(topics = "order-created", groupId = "payment-group")
    public void consume(OrderCreatedAvroEvent avroEvent) {
//        handler.handle(mapper.toAppEvent(avroEvent));
    }
}
