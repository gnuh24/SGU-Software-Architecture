package sgu.sa.application.usecase.query.getorder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgu.sa.core.repository.OrderRepository;
import sgu.sa.application.exception.OrderNotFoundException;
import sgu.sa.application.mapper.OrderDtoMapper;
import sgu.sa.application.usecase.common.RequestHandler;

@Service
@RequiredArgsConstructor
public class GetOrderHandler implements RequestHandler<GetOrderQuery, GetOrderResult> {
    private final OrderRepository orderRepository;
    private final OrderDtoMapper orderDtoMapper;

    @Override
    public GetOrderResult handle(GetOrderQuery query) {
        var order = orderRepository
            .findById(query.orderId())
            .orElseThrow(() -> new OrderNotFoundException(
                "Không tìm thấy đơn hàng có Id " + query.orderId()
            ));
        var orderDto = orderDtoMapper.toDto(order);
        return new GetOrderResult(orderDto);
    }
}
