package sgu.sa.application.usecase.query.getorder;

import sgu.sa.application.dto.OrderDTO;

public record GetOrderResult(
    OrderDTO order
) {
}
