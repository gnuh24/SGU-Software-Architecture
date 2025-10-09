package mapper;

import dto.PaymentDTO;
import entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(target = "amount", source = "amount.value")
    @Mapping(target = "currency", source = "amount.currency")
    @Mapping(target = "amountString", expression = "java(payment.getAmount().toString())")
    PaymentDTO toDto(Payment payment);

    @Mapping(target = "domainEvents", ignore = true)
    Payment toPayment(PaymentDTO paymentDTO);
}
