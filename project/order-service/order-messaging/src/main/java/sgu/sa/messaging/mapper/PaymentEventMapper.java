package sgu.sa.messaging.mapper;

import event.avro.payment.PaymentCompletedAvroEvent;
import org.mapstruct.Mapper;
import sgu.sa.application.event.paymentcompleted.PaymentCompletedEvent;

@Mapper(componentModel = "spring")
public interface PaymentEventMapper {
    PaymentCompletedEvent toAppEvent(PaymentCompletedAvroEvent avroEvent);
}
