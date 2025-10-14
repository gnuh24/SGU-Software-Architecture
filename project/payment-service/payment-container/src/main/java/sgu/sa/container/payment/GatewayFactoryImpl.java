package sgu.sa.container.payment;

import sgu.sa.application.port.payment.Gateway;
import sgu.sa.application.port.payment.GatewayFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sgu.sa.container.payment.gateway.VnPayGateway;
import sgu.sa.container.payment.gateway.ZaloPayGateway;
import sgu.sa.core.type.PaymentMethod;

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
