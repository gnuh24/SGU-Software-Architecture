package sgu.sa.application.usecase.command.createpayment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgu.sa.application.mapper.PaymentDtoMapper;
import sgu.sa.application.port.messaging.EventProducer;
import sgu.sa.application.mediator.RequestHandler;
import sgu.sa.core.entity.Payment;
import sgu.sa.core.repository.PaymentRepository;
import sgu.sa.core.vo.Money;

@Service
@RequiredArgsConstructor
public class CreatePaymentHandler implements RequestHandler<CreatePaymentCommand, CreatePaymentResult> {
    private final EventProducer eventPublisher;
    private final PaymentDtoMapper paymentMapper;
    private final PaymentRepository paymentRepo;

    @Override
    public CreatePaymentResult handle(CreatePaymentCommand command) {
        var money = new Money(command.amount(), command.currency());
        var payment = paymentRepo.save(Payment.create(command.orderId(), money, command.method()));

        eventPublisher.produceAll(payment.getDomainEvents());
        payment.clearDomainEvents();

        var paymentDto = paymentMapper.toDto(payment);
        return new CreatePaymentResult(paymentDto);
    }
}
