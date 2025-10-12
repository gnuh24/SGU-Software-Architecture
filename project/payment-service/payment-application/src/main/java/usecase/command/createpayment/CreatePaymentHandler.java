package usecase.command.createpayment;

import adapter.messaging.EventPublisher;
import entity.Payment;
import lombok.RequiredArgsConstructor;
import mapper.PaymentMapper;
import repository.PaymentRepository;
import vo.Money;

@RequiredArgsConstructor
public class CreatePaymentHandler {
    private PaymentRepository paymentRepo;
    private PaymentMapper paymentMapper;
    private EventPublisher eventPublisher;

    public CreatePaymentResult handle(CreatePaymentCommand command) {
        var money = new Money(command.amount(), command.currency());
        var payment = paymentRepo.save(Payment.create(command.orderId(), money, command.method()));

        eventPublisher.publishAll(payment.getDomainEvents());
        payment.clearDomainEvents();

        var paymentDto = paymentMapper.toDto(payment);

        return new CreatePaymentResult(paymentDto);
    }
}
