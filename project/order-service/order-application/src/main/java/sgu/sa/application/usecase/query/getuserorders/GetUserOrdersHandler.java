package sgu.sa.application.usecase.query.getuserorders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgu.sa.core.repository.OrderRepository;
import sgu.sa.application.mapper.OrderDtoMapper;
import sgu.sa.application.usecase.common.RequestHandler;

@Service
@RequiredArgsConstructor
public class GetUserOrdersHandler implements RequestHandler<GetUserOrdersQuery, GetUserOrdersResult> {
    private final OrderRepository orderRepository;
    private final OrderDtoMapper orderDtoMapper;

    @Override
    public GetUserOrdersResult handle(GetUserOrdersQuery query) {
        var orders = orderRepository.findByCustomerId(query.userId());
        var orderDtos = orderDtoMapper.toDtoList(orders);
        return new GetUserOrdersResult(orderDtos);
    }
}
