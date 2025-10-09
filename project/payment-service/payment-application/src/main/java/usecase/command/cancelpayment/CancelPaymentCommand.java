package usecase.command.cancelpayment;

import java.util.UUID;

public record CancelPaymentCommand(UUID paymentId) {
}
