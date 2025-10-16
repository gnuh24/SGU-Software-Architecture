package sgu.sa.messaging.consumer;

import event.avro.order.OrderCreatedAvroEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sgu.sa.application.usecase.command.createpayment.CreatePaymentCommand;
import sgu.sa.application.usecase.command.createpayment.CreatePaymentHandler;
import sgu.sa.core.type.Currency;
import sgu.sa.core.type.PaymentMethod;

@Component
@RequiredArgsConstructor
public class OrderCreatedKafkaConsumer {

    private final CreatePaymentHandler createPaymentHandler;

    @Transactional
    @KafkaListener(
        topics = "order-created",
        groupId = "order-service-group",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(OrderCreatedAvroEvent avroEvent) {
        var command = new CreatePaymentCommand(
            avroEvent.getOrderId(),
            avroEvent.getTotalPrice(),
            Currency.VND,
            PaymentMethod.valueOf(avroEvent.getMethod().toString())
        );
        createPaymentHandler.handle(command);
    }
}
