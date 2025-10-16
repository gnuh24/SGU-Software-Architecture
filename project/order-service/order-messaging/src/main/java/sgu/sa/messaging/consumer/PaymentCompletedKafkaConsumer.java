package sgu.sa.messaging.consumer;

import event.avro.payment.PaymentCompletedAvroEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sgu.sa.application.usecase.command.payorder.PayOrderCommand;
import sgu.sa.application.usecase.command.payorder.PayOrderHandler;

@Component
@RequiredArgsConstructor
public class PaymentCompletedKafkaConsumer {

    private final PayOrderHandler payOrderHandler;

    @Transactional
    @KafkaListener(
        topics = "payment-completed",
        groupId = "payment-service-group",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(PaymentCompletedAvroEvent avroEvent) {
        var command = new PayOrderCommand(avroEvent.getOrderId());
        payOrderHandler.handle(command);
    }
}
