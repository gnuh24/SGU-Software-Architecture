package sgu.sa.messaging.consumer;

import event.avro.payment.PaymentCompletedAvroEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sgu.sa.application.event.paymentcompleted.PaymentCompletedHandler;
import sgu.sa.messaging.mapper.PaymentEventMapper;

@Component
@RequiredArgsConstructor
public class PaymentCompletedKafkaConsumer {

    private final PaymentCompletedHandler handler;
    private final PaymentEventMapper mapper;

    @Transactional
    @KafkaListener(
        topics = "payment-completed",
        groupId = "payment-service-group",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(PaymentCompletedAvroEvent avroEvent) {
        handler.handle(mapper.toAppEvent(avroEvent));
    }
}
