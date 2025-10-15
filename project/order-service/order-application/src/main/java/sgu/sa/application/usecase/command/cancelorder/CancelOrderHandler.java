package sgu.sa.application.usecase.command.cancelorder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgu.sa.application.port.messaging.EventProducer;
import sgu.sa.application.exception.AppException;
import sgu.sa.core.repository.OrderRepository;
import sgu.sa.application.usecase.common.RequestHandler;
import sgu.sa.core.type.OrderStatus;

@Service
@RequiredArgsConstructor
public class CancelOrderHandler implements RequestHandler<CancelOrderCommand, Void> {
    private final EventProducer eventProducer;
    private final OrderRepository orderRepository;
    @Override
    public Void handle(CancelOrderCommand command) {
        var order = orderRepository.findById(command.orderId())
            .orElseThrow(() -> new AppException("Order not found"));
        order.changeStatus(OrderStatus.CANCELED);
        orderRepository.save(order);

        eventProducer.produceAll(order.getDomainEvents());
        return null;
    }
}
