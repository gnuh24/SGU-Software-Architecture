package sgu.sa.application.usecase.command.cancelpayment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgu.sa.application.exception.PaymentNotFoundException;
import sgu.sa.application.port.messaging.EventProducer;
import sgu.sa.application.mediator.RequestHandler;
import sgu.sa.core.repository.PaymentRepository;
import sgu.sa.core.type.PaymentStatus;

@Service
@RequiredArgsConstructor
public class CancelPaymentHandler implements RequestHandler<CancelPaymentCommand, Void> {

    private final PaymentRepository paymentRepository;
    private final EventProducer eventPublisher;

    @Override
    public Void handle(CancelPaymentCommand command) {
        var payment = paymentRepository
            .findByOrderId(command.orderId())
            .orElseThrow(() -> new PaymentNotFoundException(
                "Không tìm thấy đơn hàng thanh toán với Id " + command.orderId()
            ));
        payment.changeStatus(PaymentStatus.CANCELED);
        paymentRepository.save(payment);

        eventPublisher.produceAll(payment.getDomainEvents());
        payment.clearDomainEvents();
        return null;
    }
}
