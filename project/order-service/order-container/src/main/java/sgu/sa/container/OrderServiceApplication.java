package sgu.sa.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackages = "sgu.sa.infrastructure.persistence")
@EnableJpaRepositories(basePackages = {
    "sgu.sa.infrastructure.repository.jpa"
})
@ComponentScan(basePackages = {
    "sgu.sa.application",
    "sgu.sa.infrastructure",
    "sgu.sa.messaging",
    "sgu.sa.container"
})
public class OrderServiceApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
