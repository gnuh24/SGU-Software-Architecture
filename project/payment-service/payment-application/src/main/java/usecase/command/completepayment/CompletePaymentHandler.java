package usecase.command.completepayment;

import adapter.messaging.EventPublisher;
import exception.PaymentNotFoundException;
import lombok.AllArgsConstructor;
import repository.PaymentRepository;
import type.PaymentStatus;

@AllArgsConstructor
public class CompletePaymentHandler {
    private final PaymentRepository paymentRepo;
    private final EventPublisher eventPublisher;

    public void handle(CompletePaymentCommand command) {
        var payment = paymentRepo
            .findById(command.paymentId())
            .orElseThrow(() -> new PaymentNotFoundException(
                "Không tìm thấy thanh toán với Id " + command.paymentId()
            ));
        payment.changeStatus(PaymentStatus.COMPLETED);
        paymentRepo.save(payment);

        eventPublisher.publishAll(payment.getDomainEvents());
        payment.clearDomainEvents();
    }
}
