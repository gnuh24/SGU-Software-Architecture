package sgu.sa.messaging.mapper;

import event.payment.*;
import sgu.sa.core.event.*;

//@Mapper
public interface PaymentEventMapper {
    PaymentCompletedAvroEvent toAvro(PaymentCompleted event);
    PaymentCanceledAvroEvent toAvro(PaymentCanceled event);
    PaymentCreatedAvroEvent toAvro(PaymentCreated event);
    PaymentRefundedAvroEvent toAvro(PaymentRefunded event);
    PaymentExpiredAvroEvent toAvro(PaymentExpired event);
}
