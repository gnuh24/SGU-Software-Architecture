package sgu.sa.application.usecase.query.getstatistics;

import java.math.BigDecimal;

public record GetOrderStatisticsResult(
        long totalOrders,
        BigDecimal totalRevenue,
        BigDecimal averageOrderValue) {
}
