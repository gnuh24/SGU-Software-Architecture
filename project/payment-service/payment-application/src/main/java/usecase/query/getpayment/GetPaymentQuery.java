package usecase.query.getpayment;

import java.util.UUID;

public record GetPaymentQuery(
    UUID paymentId
) {
}
