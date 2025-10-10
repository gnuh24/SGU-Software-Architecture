package usecase.command.refundpayment;

import adapter.messaging.EventPublisher;
import exception.PaymentNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import repository.PaymentRepository;
import type.PaymentStatus;

@RequiredArgsConstructor
public class CancelPaymentHandler {
    private final PaymentRepository paymentRepo;
    private final EventPublisher eventPublisher;


    public void handle(CancelPaymentCommand command) {
        var payment = paymentRepo.findById(command.paymentId()).orElse(null);
        if (payment == null) {
            throw new PaymentNotFoundException(
                "Không tìm thấy thanh toán với Id" + command.paymentId()
            );
        }
        payment.changeStatus(PaymentStatus.CANCELED);
        paymentRepo.save(payment);

        eventPublisher.publishAll(payment.getDomainEvents());
        payment.clearDomainEvents();
    }
}
