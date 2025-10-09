package usecase.command.completepayment;

import java.util.UUID;

public record CompletePaymentCommand(UUID paymentId) {
}
