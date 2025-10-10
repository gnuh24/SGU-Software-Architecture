package mapper;

import dto.PaymentDTO;
import entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PaymentMapper {
    @Mapping(target = "value", source = "amount.value")
    @Mapping(target = "currency", source = "amount.currency")
    @Mapping(target = "amountString", expression = "java(payment.getAmount().toString())")
    PaymentDTO toDto(Payment payment);

    @Mapping(target = "amount", expression = "java(new Money(paymentDTO.value(), paymentDTO.currency()))")
    @Mapping(target = "domainEvents", ignore = true)
    Payment toPayment(PaymentDTO paymentDTO);
}
