package sgu.sa.application.usecase.command.createpayment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgu.sa.application.mapper.PaymentDtoMapper;
import sgu.sa.application.port.messaging.EventPublisher;
import sgu.sa.application.usecase.common.RequestHandler;
import sgu.sa.core.entity.Payment;
import sgu.sa.core.repository.PaymentRepository;
import sgu.sa.core.vo.Money;

@Service
@RequiredArgsConstructor
public class CreatePaymentHandler implements RequestHandler<CreatePaymentCommand, CreatePaymentResult> {
    private final EventPublisher eventPublisher;
    private final PaymentDtoMapper paymentMapper;
    private final PaymentRepository paymentRepo;

    @Override
    public CreatePaymentResult handle(CreatePaymentCommand command) {
        var money = new Money(command.amount(), command.currency());
        var payment = paymentRepo.save(Payment.create(command.orderId(), money, command.method()));

        eventPublisher.publishAll(payment.getDomainEvents());
        payment.clearDomainEvents();

        var paymentDto = paymentMapper.toDto(payment);
        return new CreatePaymentResult(paymentDto);
    }
}
