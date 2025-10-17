package sgu.sa.application.usecase.query.getstatistics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgu.sa.application.usecase.common.RequestHandler;
import sgu.sa.core.repository.OrderRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class GetOrderStatisticsHandler implements RequestHandler<GetOrderStatisticsQuery, GetOrderStatisticsResult> {
    private final OrderRepository orderRepository;

    @Override
    public GetOrderStatisticsResult handle(GetOrderStatisticsQuery query) {
        // Get all orders
        var orders = orderRepository.findAll();

        // Calculate statistics
        long totalOrders = orders.size();

        BigDecimal totalRevenue = orders.stream()
                .map(order -> order.getTotalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal averageOrderValue = totalOrders > 0
                ? totalRevenue.divide(BigDecimal.valueOf(totalOrders), 2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;

        return new GetOrderStatisticsResult(totalOrders, totalRevenue, averageOrderValue);
    }
}
