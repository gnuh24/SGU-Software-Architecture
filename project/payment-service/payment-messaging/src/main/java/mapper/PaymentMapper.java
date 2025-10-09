package mapper;

import event.*;
import event.payment.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentCompletedAvroEvent toAvro(PaymentCompleted event);
    PaymentCanceledAvroEvent toAvro(PaymentCanceled event);
    PaymentCreatedAvroEvent toAvro(PaymentCreated event);
    PaymentRefundedAvroEvent toAvro(PaymentRefunded event);
    PaymentExpiredAvroEvent toAvro(PaymentExpired event);
}
