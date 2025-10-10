package mapper;

import entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import persistence.entity.PaymentEntity;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "domainEvents", ignore = true)
    @Mapping(target = "amount", expression = "java(new Money(entity.getValue(), entity.getCurrency()))")
    Payment toDomain(PaymentEntity entity);

    @Mapping(target = "currency", source = "amount.currency")
    @Mapping(target = "value", source = "amount.value")
    PaymentEntity toEntity(Payment domain);

    List<Payment> toDomainList(List<PaymentEntity> entities);

    List<PaymentEntity> toEntityList(List<Payment> domains);
}