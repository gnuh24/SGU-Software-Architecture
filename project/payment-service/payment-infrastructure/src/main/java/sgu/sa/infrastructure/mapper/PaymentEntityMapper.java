package sgu.sa.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sgu.sa.core.entity.Payment;
import sgu.sa.infrastructure.persistence.entity.PaymentEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentEntityMapper {
    @Mapping(target = "domainEvents", ignore = true)
    Payment toDomain(PaymentEntity entity);

    @Mapping(target = "domainEvents", ignore = true)
    PaymentEntity toEntity(Payment domain);
    List<Payment> toDomainList(List<PaymentEntity> entities);
    List<PaymentEntity> toEntityList(List<Payment> domains);
}