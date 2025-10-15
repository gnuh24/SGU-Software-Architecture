package sgu.sa.application.usecase.query.getuserorders;

import sgu.sa.application.dto.OrderDTO;

import java.util.List;

public record GetUserOrdersResult(List<OrderDTO> orders) {
}
