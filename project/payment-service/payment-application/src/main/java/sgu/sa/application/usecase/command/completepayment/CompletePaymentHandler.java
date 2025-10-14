package sgu.sa.application.usecase.command.completepayment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgu.sa.application.exception.PaymentNotFoundException;
import sgu.sa.application.port.messaging.EventPublisher;
import sgu.sa.application.usecase.common.RequestHandler;
import sgu.sa.core.repository.PaymentRepository;
import sgu.sa.core.type.PaymentStatus;

@Service
@RequiredArgsConstructor
public class CompletePaymentHandler implements RequestHandler<CompletePaymentCommand, Void> {

    private final PaymentRepository paymentRepository;
    private final EventPublisher eventPublisher;

    @Override
    @Transactional
    public Void handle(CompletePaymentCommand command) {
        var payment = paymentRepository
            .findById(command.paymentId())
            .orElseThrow(() -> new PaymentNotFoundException(
                "Không tìm thấy thanh toán với Id " + command.paymentId()
            ));
        payment.changeStatus(PaymentStatus.COMPLETED);
        paymentRepository.save(payment);

        eventPublisher.publishAll(payment.getDomainEvents());
        payment.clearDomainEvents();
        return null;
    }
}
