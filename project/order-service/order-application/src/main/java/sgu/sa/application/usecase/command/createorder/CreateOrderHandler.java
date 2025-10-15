package sgu.sa.application.usecase.command.createorder;

import sgu.sa.core.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgu.sa.core.repository.OrderRepository;
import sgu.sa.application.mapper.OrderDtoMapper;
import sgu.sa.application.mapper.OrderItemDtoMapper;
import sgu.sa.application.usecase.common.RequestHandler;

@Service
@RequiredArgsConstructor
public class CreateOrderHandler implements RequestHandler<CreateOrderCommand, CreateOrderResult> {
    private final OrderRepository
        orderRepository;
    private final OrderDtoMapper orderMapper;
    private final OrderItemDtoMapper orderItemMapper;

    public CreateOrderResult handle(CreateOrderCommand command) {
        var order = Order.create(
            command.userId(),
            command.restaurantId(),
            command.method(),
            command.address()
        );
        var items = orderItemMapper.toDomainList(command.items());
        order.addItems(items);
        var saved = orderRepository.save(order);

        return new CreateOrderResult(orderMapper.toDto(saved));
    }
}
