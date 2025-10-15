package sgu.sa.application.usecase.command.payorder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgu.sa.core.repository.OrderRepository;
import sgu.sa.application.usecase.common.RequestHandler;
import sgu.sa.core.type.OrderStatus;

@Service
@RequiredArgsConstructor
public class PayOrderHandler implements RequestHandler<PayOrderCommand, Void> {
    private final OrderRepository orderRepository;

    @Override
    public Void handle(PayOrderCommand command) {
        var order = orderRepository.findById(command.orderId())
            .orElseThrow(() -> new RuntimeException("Order not found"));

        order.changeStatus(OrderStatus.PAID);
        orderRepository.save(order);
        return null;
    }
}