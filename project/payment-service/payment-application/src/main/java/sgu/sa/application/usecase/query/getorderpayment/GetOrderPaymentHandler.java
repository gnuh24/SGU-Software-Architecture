package sgu.sa.application.usecase.query.getorderpayment;

import lombok.RequiredArgsConstructor;
import sgu.sa.application.exception.PaymentNotFoundException;
import sgu.sa.application.mapper.PaymentDtoMapper;
import sgu.sa.application.mediator.HandlerComponent;
import sgu.sa.application.mediator.RequestHandler;
import sgu.sa.core.repository.PaymentRepository;

@HandlerComponent
@RequiredArgsConstructor
public class GetOrderPaymentHandler implements RequestHandler<GetOrderPaymentQuery, GetOrderPaymentResult> {
    private final PaymentRepository paymentRepo;
    private final PaymentDtoMapper paymentMapper;
    @Override
    public GetOrderPaymentResult handle(GetOrderPaymentQuery query) {
        var payment = paymentRepo
            .findByOrderId(query.orderId())
            .orElseThrow(() -> new PaymentNotFoundException(
                "Không tìm thấy thanh toán của hóa đơn có Id " + query.orderId()
            ));
        var paymentDto = paymentMapper.toDto(payment);
        return new GetOrderPaymentResult(paymentDto);
    }
}

