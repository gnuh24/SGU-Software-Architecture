package sgu.sa.application.usecase.command.cancelpayment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgu.sa.application.exception.PaymentNotFoundException;
import sgu.sa.application.port.messaging.EventPublisher;
import sgu.sa.application.usecase.common.RequestHandler;
import sgu.sa.core.repository.PaymentRepository;
import sgu.sa.core.type.PaymentStatus;

@Service
@RequiredArgsConstructor
public class CancelPaymentHandler implements RequestHandler<CancelPaymentCommand, Void> {

    private final PaymentRepository paymentRepository;
    private final EventPublisher eventPublisher;

    @Override
    public Void handle(CancelPaymentCommand command) {
        var payment = paymentRepository
            .findByOrderId(command.orderId())
            .orElseThrow(() -> new PaymentNotFoundException(
                "Không tìm thấy đơn hàng thanh toán với Id " + command.orderId()
            ));
        payment.changeStatus(PaymentStatus.CANCELED);
        paymentRepository.save(payment);

        eventPublisher.publishAll(payment.getDomainEvents());
        payment.clearDomainEvents();
        return null;
    }
}
