package sgu.sa.messaging.consumer;

import event.avro.order.OrderCanceledAvroEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sgu.sa.application.usecase.command.cancelpayment.CancelPaymentCommand;
import sgu.sa.application.usecase.command.cancelpayment.CancelPaymentHandler;

@Component
@RequiredArgsConstructor
public class OrderCanceledKafkaConsumer {

    private final CancelPaymentHandler cancelPaymentHandler;

    @Transactional
    @KafkaListener(
        topics = "order-canceled",
        groupId = "order-service-group",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(OrderCanceledAvroEvent avroEvent) {
        var command = new CancelPaymentCommand(avroEvent.getOrderId());
        cancelPaymentHandler.handle(command);
    }
}
