package usecase.query.getorderpayment;

import java.util.UUID;

public record GetOrderPaymentQuery(
    UUID orderId
) {

}
