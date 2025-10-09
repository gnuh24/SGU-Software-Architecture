package usecase.query.getorderpayment;

import exception.PaymentNotFoundException;
import lombok.AllArgsConstructor;
import mapper.PaymentMapper;
import repository.PaymentRepository;

@AllArgsConstructor
public class GetOrderPaymentHandler {
    PaymentRepository paymentRepo;
    PaymentMapper paymentMapper;

    public GetOrderPaymentResult handle(GetOrderPaymentQuery query) {
        var payment = paymentRepo
            .findById(query.orderId())
            .orElseThrow(() -> new PaymentNotFoundException(
                "Không tìm thấy thanh toán của hóa đơn có Id " + query.orderId()
            ));
        var paymentDto = paymentMapper.toDto(payment);
        return new GetOrderPaymentResult(paymentDto);
    }
}

