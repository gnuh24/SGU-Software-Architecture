package sgu.sa.application.mapper;

import sgu.sa.application.dto.PaymentDTO;
import sgu.sa.core.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentDtoMapper{
    @Mapping(target = "amountString", expression = "java(payment.getAmount().toString())")
    PaymentDTO toDto(Payment payment);

    @Mapping(target = "domainEvents", ignore = true)
    Payment toDomain(PaymentDTO paymentDTO);
}