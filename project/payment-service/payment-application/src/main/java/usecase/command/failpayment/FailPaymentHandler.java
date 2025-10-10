package usecase.command.failpayment;

import adapter.messaging.EventPublisher;
import exception.PaymentNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import repository.PaymentRepository;
import type.PaymentStatus;

@RequiredArgsConstructor
public class FailPaymentHandler {
    private final PaymentRepository paymentRepo;
    private final EventPublisher eventPublisher;


    public void handle(FailPaymentCommand command) {
        var payment = paymentRepo
            .findById(command.paymentId())
            .orElseThrow(() -> new PaymentNotFoundException(
                "Không tìm thấy thanh toán với Id " + command.paymentId()
            ));
        payment.changeStatus(PaymentStatus.FAILED);
        paymentRepo.save(payment);

        eventPublisher.publishAll(payment.getDomainEvents());
        payment.clearDomainEvents();
    }
}
