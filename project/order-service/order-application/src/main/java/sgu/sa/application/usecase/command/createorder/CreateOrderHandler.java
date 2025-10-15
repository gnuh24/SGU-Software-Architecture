package sgu.sa.application.usecase.command.createorder;

import sgu.sa.application.port.messaging.EventProducer;
import sgu.sa.core.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgu.sa.core.repository.OrderRepository;
import sgu.sa.application.mapper.OrderDtoMapper;
import sgu.sa.application.usecase.common.RequestHandler;

@Service
@RequiredArgsConstructor
public class CreateOrderHandler implements RequestHandler<CreateOrderCommand, CreateOrderResult> {
    private final OrderRepository orderRepository;
    private final OrderDtoMapper orderMapper;
    private final EventProducer eventPublisher;

    public CreateOrderResult handle(CreateOrderCommand command) {
        var order = Order.create(
            command.userId(),
            command.restaurantId(),
            command.method(),
            command.address(),
            command.items()
        );
        var saved = orderRepository.save(order);

        eventPublisher.produceAll(order.getDomainEvents());
        return new CreateOrderResult(orderMapper.toDto(saved));
    }
}
