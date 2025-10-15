package sgu.sa.application.usecase.command.createorder;

import sgu.sa.application.usecase.common.Request;
import sgu.sa.core.type.PaymentMethod;
import sgu.sa.core.vo.Address;
import sgu.sa.core.vo.ItemData;

import java.util.List;
import java.util.UUID;

public record CreateOrderCommand(
    List<ItemData> items,
    PaymentMethod method,
    UUID userId,
    UUID restaurantId,
    Address address
) implements Request<CreateOrderResult> {
}
