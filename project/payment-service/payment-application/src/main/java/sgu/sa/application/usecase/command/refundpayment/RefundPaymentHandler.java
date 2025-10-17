package sgu.sa.application.usecase.command.refundpayment;

import lombok.RequiredArgsConstructor;
import sgu.sa.application.exception.PaymentNotFoundException;
import sgu.sa.application.mediator.HandlerComponent;
import sgu.sa.application.mediator.RequestHandler;
import sgu.sa.application.port.messaging.EventProducer;
import sgu.sa.core.repository.PaymentRepository;
import sgu.sa.core.type.PaymentStatus;

@HandlerComponent
@RequiredArgsConstructor
public class RefundPaymentHandler
 implements RequestHandler<RefundPaymentCommand, Void> {
    private final PaymentRepository paymentRepository;
    private final EventProducer eventPublisher;

    @Override
    public Void handle(RefundPaymentCommand command) {
        var payment = paymentRepository.findById(command.paymentId()).orElse(null);
        if (payment == null) {
            throw new PaymentNotFoundException(
                "Không tìm thấy thanh toán với Id" + command.paymentId()
            );
        }
        payment.changeStatus(PaymentStatus.REFUNDED);
        paymentRepository.save(payment);

        eventPublisher.produceAll(payment.getDomainEvents());
        payment.clearDomainEvents();
        return null;
    }
}
