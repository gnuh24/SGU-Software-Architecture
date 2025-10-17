package sgu.sa.application.usecase.command.verifypayment;

import lombok.RequiredArgsConstructor;
import sgu.sa.application.exception.PaymentNotFoundException;
import sgu.sa.application.mediator.HandlerComponent;
import sgu.sa.application.mediator.RequestHandler;
import sgu.sa.application.port.messaging.EventProducer;
import sgu.sa.application.port.payment.GatewayFactory;
import sgu.sa.core.repository.PaymentRepository;

@HandlerComponent
@RequiredArgsConstructor
public class VerifyPaymentHandler implements RequestHandler<VerifyPaymentCommand, Void> {
    private final GatewayFactory gatewayFactory;
    private final PaymentRepository paymentRepository;
    private final EventProducer eventProducer;

    @Override
    public Void handle(VerifyPaymentCommand command) {
        var gateWay = gatewayFactory.getGateway(command.method());
        gateWay.handleCallback(command.params(), (id, status) -> {
            var payment = paymentRepository
                .findByOrderId(id)
                .orElseThrow(() -> new PaymentNotFoundException(
                    "Không tìm thấy đơn thanh toán với Id " + id
                ));

            payment.changeStatus(status);
            paymentRepository.save(payment);

            eventProducer.produceAll(payment.getDomainEvents());
        });
        return null;
    }
}
