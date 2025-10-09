package mapper;

import entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import persistence.entity.PaymentEntity;
import type.Currency;
import vo.Money;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "domainEvents", ignore = true)
    Payment toDomain(PaymentEntity entity);

    @Mapping(target = "amount", source = "amount.value")
    @Mapping(target = "currency", source = "amount.currency")
    PaymentEntity toEntity(Payment domain);

    List<Payment> toDomainList(List<PaymentEntity> entities);

    List<PaymentEntity> toEntityList(List<Payment> domains);

}
