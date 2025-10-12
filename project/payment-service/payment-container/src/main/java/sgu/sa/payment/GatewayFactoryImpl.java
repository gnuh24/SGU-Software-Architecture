package sgu.sa.payment;

import adapter.payment.Gateway;
import adapter.payment.GatewayFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sgu.sa.payment.gateway.VnPayGateway;
import sgu.sa.payment.gateway.ZaloPayGateway;
import type.PaymentMethod;

@Component
@RequiredArgsConstructor
@Getter
public class GatewayFactoryImpl implements GatewayFactory {
    private final VnPayGateway vnPayGateway;
    private final ZaloPayGateway zaloPayGateway;

    @Override
    public Gateway getGateway(PaymentMethod method) {
        return switch (method) {
            case VN_PAY -> getVnPayGateway();
            case ZALO_PAY -> getZaloPayGateway();
            default -> throw new RuntimeException("Invalid method");
        };
    }
}
