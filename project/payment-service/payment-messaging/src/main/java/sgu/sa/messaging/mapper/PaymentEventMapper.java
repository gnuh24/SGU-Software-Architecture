package sgu.sa.messaging.mapper;

import event.avro.payment.*;
import event.payment.PaymentCreatedAvroEvent;
import org.mapstruct.Mapper;
import sgu.sa.core.event.*;

@Mapper(componentModel = "spring")
public interface PaymentEventMapper {
    PaymentCompletedAvroEvent toAvro(PaymentCompletedEvent event);
    PaymentCanceledAvroEvent toAvro(PaymentCanceledEvent event);
    PaymentCreatedAvroEvent toAvro(PaymentCreatedEvent event);
    PaymentRefundedAvroEvent toAvro(PaymentRefundedEvent event);
    PaymentFailedAvroEvent toAvro(PaymentFailedEvent event);
    PaymentExpiredAvroEvent toAvro(PaymentExpiredEvent event);
}
