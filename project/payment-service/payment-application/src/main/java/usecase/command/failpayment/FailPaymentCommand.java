package usecase.command.failpayment;

import java.util.UUID;

public record FailPaymentCommand(UUID paymentId) {
}
