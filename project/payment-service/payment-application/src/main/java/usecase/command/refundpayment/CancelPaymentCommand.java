package usecase.command.refundpayment;

import java.util.UUID;

public record CancelPaymentCommand(UUID paymentId) {
}
