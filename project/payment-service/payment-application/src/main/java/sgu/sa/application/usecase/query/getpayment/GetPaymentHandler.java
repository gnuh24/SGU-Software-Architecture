package sgu.sa.application.usecase.query.getpayment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgu.sa.application.exception.PaymentNotFoundException;
import sgu.sa.application.mapper.PaymentDtoMapper;
import sgu.sa.application.usecase.common.RequestHandler;
import sgu.sa.core.repository.PaymentRepository;
@Service
@RequiredArgsConstructor
public class GetPaymentHandler implements RequestHandler<GetPaymentQuery, GetPaymentResult> {
    private final PaymentRepository paymentRepo;
    private final PaymentDtoMapper paymentMapper;
    @Override
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
