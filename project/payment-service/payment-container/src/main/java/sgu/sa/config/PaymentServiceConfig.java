package sgu.sa.config;

import adapter.payment.GatewayFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import usecase.command.createpaymenturl.CreatePaymentUrlHandler;

@Configuration
public class PaymentServiceConfig {
    @Bean
    public CreatePaymentUrlHandler createPaymentUrlHandler(GatewayFactory factory) {
        return new CreatePaymentUrlHandler(factory);
    }

}
