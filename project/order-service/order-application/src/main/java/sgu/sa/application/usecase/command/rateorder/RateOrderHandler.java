package sgu.sa.application.usecase.command.rateorder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgu.sa.core.repository.OrderRepository;
import sgu.sa.application.usecase.common.RequestHandler;

@Service
@RequiredArgsConstructor
public class RateOrderHandler implements RequestHandler<RateOrderCommand, Void> {
    private final OrderRepository orderRepository;

    @Override
    public Void handle(RateOrderCommand command) {
        var order = orderRepository.findById(command.orderId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));

        order.rateOrder(command.rating());
        orderRepository.save(order);
        return null;
    }
}
