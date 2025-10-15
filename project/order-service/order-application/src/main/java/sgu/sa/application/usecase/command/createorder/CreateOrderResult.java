package sgu.sa.application.usecase.command.createorder;

import sgu.sa.application.dto.OrderDTO;

public record CreateOrderResult(
    OrderDTO order
) {
}
