package usecase.query.getpayment;

import exception.PaymentNotFoundException;
import lombok.AllArgsConstructor;
import mapper.PaymentMapper;
import repository.PaymentRepository;

@AllArgsConstructor
public class GetPaymentHandler {
    PaymentRepository paymentRepo;
    PaymentMapper paymentMapper;

    public GetPaymentResult handle(GetPaymentQuery query) {
        var payment = paymentRepo
            .findById(query.paymentId())
            .orElseThrow(() -> new PaymentNotFoundException(
                "Không tìm thấy thanh toán với Id " + query.paymentId()
            ));
        var paymentDto = paymentMapper.toDto(payment);
        return new GetPaymentResult(paymentDto);
    }
}
