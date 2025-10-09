package usecase.command.expirepayment;

import java.util.UUID;

public record ExpirePaymentCommand(UUID paymentId) {
}
